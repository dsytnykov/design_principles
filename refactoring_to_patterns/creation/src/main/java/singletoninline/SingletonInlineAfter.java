package singletoninline;

// AFTER REFACTORING
// Solution: Remove Singleton, create the object normally and pass it
// via constructor injection — dependencies become explicit and testable

public class SingletonInlineAfter {
    public static void main(String[] args) {
        // One clear place where the object is created and owned
        DatabaseConnection db = new DatabaseConnection("localhost", 5432, "shop_db");

        // Dependencies are visible and explicit
        ProductRepository productRepo = new ProductRepository(db);
        OrderRepository orderRepo = new OrderRepository(db);

        productRepo.saveProduct("Laptop", 999.99);
        productRepo.findProduct("Laptop");
        orderRepo.saveOrder(1001, 999.99);

        // Bonus: now trivially easy to use a second database
        DatabaseConnection analyticsDb = new DatabaseConnection("analytics-host", 5432, "analytics_db");
        OrderRepository analyticsRepo = new OrderRepository(analyticsDb);
        analyticsRepo.saveOrder(1001, 999.99);

        // Bonus: easy to test — just pass in a mock DatabaseConnection
        // ProductRepository testRepo = new ProductRepository(new MockDatabaseConnection());
    }
}


class DatabaseConnection {
    private final String host;
    private final int port;
    private final String databaseName;
    private boolean isConnected;

    public DatabaseConnection(String host, int port, String databaseName) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
        this.isConnected = false;
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

    public String getDatabaseName() { return databaseName; }
}

class ProductRepository {
    private final DatabaseConnection db;

    public ProductRepository(DatabaseConnection db) {
        this.db = db;
    }

    public void saveProduct(String productName, double price) {
        if (!db.isConnected()) {
            db.connect();
        }
        System.out.println("Saving product: " + productName + " ($" + price + ") to "
                + db.getDatabaseName());
    }

    public String findProduct(String productName) {
        if (!db.isConnected()) {
            db.connect();
        }
        System.out.println("Finding product: " + productName + " in " + db.getDatabaseName());
        return productName;
    }
}

class OrderRepository {
    private final DatabaseConnection db;

    public OrderRepository(DatabaseConnection db) {
        this.db = db;
    }

    public void saveOrder(int orderId, double total) {
        if (!db.isConnected()) {
            db.connect();
        }
        System.out.println("Saving order #" + orderId + " ($" + total + ") to "
                + db.getDatabaseName());
    }
}

