package singletoninline;

// BEFORE REFACTORING
// Problem: Singleton is used unnecessarily, making the code tightly coupled,
// hard to test, and hiding dependencies

public class SingletonInlineBefore {
    public static void main(String[] args) {
        // Singleton accessed implicitly everywhere — no clear ownership
        ProductRepositoryBefore productRepo = new ProductRepositoryBefore();
        OrderRepositoryBefore orderRepo = new OrderRepositoryBefore();

        productRepo.saveProduct("Laptop", 999.99);
        productRepo.findProduct("Laptop");
        orderRepo.saveOrder(1001, 999.99);

        // Hard to test - you can't swap DatabaseRegistry for a mock
        // Hard to use two different databases at once
        // Dependencies are hidden - not visible from constructor
    }
}

class DatabaseRegistry {
    // Singleton instance
    private static DatabaseRegistry instance;

    private final String host;
    private final int port;
    private final String databaseName;
    private boolean isConnected;

    // Private constructor
    private DatabaseRegistry() {
        this.host = "localhost";
        this.port = 5432;
        this.databaseName = "shop_db";
        this.isConnected = false;
    }

    // Global access point
    public static DatabaseRegistry getInstance() {
        if (instance == null) {
            instance = new DatabaseRegistry();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connecting to " + host + ":" + port + "/" + databaseName);
        isConnected = true;
    }

    public void disconnect() {
        System.out.println("Disconnecting from database");
        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getDatabaseName() { return databaseName; }
}

// Client classes access Singleton globally — tight coupling, hidden dependency
class ProductRepositoryBefore {
    public void saveProduct(String productName, double price) {
        // Fetches Singleton directly — hidden dependency!
        DatabaseRegistry db = DatabaseRegistry.getInstance();
        if (!db.isConnected()) {
            db.connect();
        }
        System.out.println("Saving product: " + productName + " ($" + price + ") to "
                + db.getDatabaseName());
    }

    public String findProduct(String productName) {
        DatabaseRegistry db = DatabaseRegistry.getInstance();
        if (!db.isConnected()) {
            db.connect();
        }
        System.out.println("Finding product: " + productName + " in " + db.getDatabaseName());
        return productName;
    }
}

class OrderRepositoryBefore {
    public void saveOrder(int orderId, double total) {
        // Same hidden dependency repeated
        DatabaseRegistry db = DatabaseRegistry.getInstance();
        if (!db.isConnected()) {
            db.connect();
        }
        System.out.println("Saving order #" + orderId + " ($" + total + ") to "
                + db.getDatabaseName());
    }
}

