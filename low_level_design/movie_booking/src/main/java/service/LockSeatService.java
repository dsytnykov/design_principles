package service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LockSeatService {

    private final ConcurrentHashMap<String, Expiry> locks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService sweeper = Executors.newSingleThreadScheduledExecutor();

    public LockSeatService() {
        sweeper.scheduleAtFixedRate(this::sweep, 1, 1, TimeUnit.MINUTES);
    }

    private void sweep() {
        long now = System.currentTimeMillis();
        locks.entrySet().removeIf(e -> e.getValue().deadline <= now);
    }

    public boolean tryLock(String key, String userId, long ttl) {
        long now = System.currentTimeMillis();
        Expiry expiry = new Expiry(now + ttl, userId);
        return locks.compute(key, (k, v) -> (v == null || v.deadline <= now) ? expiry : v) == expiry;
    }

    public void unlock(String key) {
        locks.remove(key);
    }

    public boolean isLockExpired(String key) {
        Expiry expiry = locks.get(key);
        return expiry != null && expiry.deadline <= System.currentTimeMillis();
    }

    public boolean isLockedBy(String key, String userId) {
        Expiry expiry = locks.get(key);
        return expiry != null && expiry.owner.equals(userId);
    }

    public void close() {
        sweeper.shutdown();
    }

    private record Expiry(long deadline, String owner) { }
}
