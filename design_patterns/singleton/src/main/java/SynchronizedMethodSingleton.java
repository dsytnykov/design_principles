public class SynchronizedMethodSingleton {
    private static SynchronizedMethodSingleton instance;
    private SynchronizedMethodSingleton() {}

    public static synchronized SynchronizedMethodSingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedMethodSingleton();
        }
        return instance;
    }
}
