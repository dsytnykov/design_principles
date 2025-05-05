public class SynchronizedBlockSingleton {
    private static volatile SynchronizedBlockSingleton instance;
    private SynchronizedBlockSingleton() {}

    public static SynchronizedBlockSingleton getInstance() {
        if(instance == null) {
            synchronized (SynchronizedBlockSingleton.class) {
                if(instance == null) {
                    instance = new SynchronizedBlockSingleton();
                }
            }
        }
        return instance;
    }

    // other methods
}
