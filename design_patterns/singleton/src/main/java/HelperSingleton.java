public class HelperSingleton {
    private HelperSingleton() {}

    private static final class InstanceHolder {
        private static final HelperSingleton instance = new HelperSingleton();
    }

    public static HelperSingleton getInstance() {
        return InstanceHolder.instance;
    }

    //other methods
}
