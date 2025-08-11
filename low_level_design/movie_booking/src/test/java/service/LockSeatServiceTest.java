package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LockSeatServiceTest {

    private LockSeatService lockSeatService;

    @BeforeEach
    void setUp() {
        lockSeatService = new LockSeatService();
    }
    @Test
    void shouldLockSeatSuccessfully() {
        assertTrue(lockSeatService.tryLock("1", "1", 1000));
    }

    @Test
    void shouldFailLockSeat() {
        assertTrue(lockSeatService.tryLock("1", "1", 1000));
        assertFalse(lockSeatService.tryLock("1", "2", 1000));
    }

    @Test
    void shouldLockAfterFirstExpire() throws InterruptedException {
        assertTrue(lockSeatService.tryLock("1", "1", 1000));
        Thread.sleep(1100);
        assertTrue(lockSeatService.tryLock("1", "2", 1000));
    }

    @Test
    void shouldUnlockSeatSuccessfully() {
        assertTrue(lockSeatService.tryLock("1", "1", 1000));
        assertNotNull(lockSeatService.unlock("1"));
    }

    @Test
    void shouldFailUnlockSeat() {
        assertTrue(lockSeatService.tryLock("1", "1", 1000));
        assertNull(lockSeatService.unlock("2"));
    }

    @Test
    void shouldCheckIfLockExpired() throws InterruptedException {
        assertTrue(lockSeatService.tryLock("1", "1", 1000));
        assertFalse(lockSeatService.isLockExpired("1"));
        Thread.sleep(1100);
        assertTrue(lockSeatService.isLockExpired("1"));
    }

    @Test
    void shouldCheckIfLockedByUser() {
        assertTrue(lockSeatService.tryLock("1", "1", 1000));
        assertTrue(lockSeatService.isLockedBy("1", "1"));
        assertFalse(lockSeatService.isLockedBy("1", "2"));
    }

}