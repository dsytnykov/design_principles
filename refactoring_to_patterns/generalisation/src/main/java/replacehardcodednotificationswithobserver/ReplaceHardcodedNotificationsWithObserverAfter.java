package replacehardcodednotificationswithobserver;

// AFTER REFACTORING
// Solution: Observer pattern - loose coupling, flexible notification system

import java.util.*;

public class ReplaceHardcodedNotificationsWithObserverAfter {
    public static void main(String[] args) {
        InventorySystemAfter inventory = new InventorySystemAfter("Laptop", 50);

        // Register observers as needed - easy to add or remove
        inventory.addObserver(new EmailObserver());
        inventory.addObserver(new SMSObserver());
        inventory.addObserver(new DashboardObserver());
        inventory.addObserver(new SlackObserver());
        inventory.addObserver(new AnalyticsObserver());

        System.out.println("=== Initial stock operations ===");
        inventory.removeStock(10);

        System.out.println("\n=== Low stock trigger ===");
        inventory.removeStock(35);  // Triggers low stock alerts

        System.out.println("\n=== Restocking ===");
        inventory.addStock(20);

        // Can dynamically remove observers
        System.out.println("\n=== After removing email observer ===");
        inventory.removeObserver(new EmailObserver());
        inventory.removeStock(5);
    }
}

interface StockObserver {
    void onStockChanged(String productName, int oldLevel, int newLevel);
}

class InventorySystemAfter {
    private int stockLevel;
    private String productName;
    private List<StockObserver> observers;

    public InventorySystemAfter(String productName, int initialStock) {
        this.productName = productName;
        this.stockLevel = initialStock;
        this.observers = new ArrayList<>();
    }

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(int oldLevel, int newLevel) {
        for (StockObserver observer : observers) {
            observer.onStockChanged(productName, oldLevel, newLevel);
        }
    }

    public void removeStock(int quantity) {
        int oldLevel = stockLevel;
        stockLevel -= quantity;
        System.out.println("Removed " + quantity + " units. New stock: " + stockLevel);
        notifyObservers(oldLevel, stockLevel);
    }

    public void addStock(int quantity) {
        int oldLevel = stockLevel;
        stockLevel += quantity;
        System.out.println("Added " + quantity + " units. New stock: " + stockLevel);
        notifyObservers(oldLevel, stockLevel);
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public String getProductName() {
        return productName;
    }
}

class EmailObserver implements StockObserver {
    private static final int LOW_STOCK_THRESHOLD = 10;

    @Override
    public void onStockChanged(String productName, int oldLevel, int newLevel) {
        if (newLevel < LOW_STOCK_THRESHOLD && oldLevel >= LOW_STOCK_THRESHOLD) {
            System.out.println("  [EMAIL] Alert: " + productName + " is low on stock (" + newLevel + " units)");
        }
    }
}

class SMSObserver implements StockObserver {
    private static final int LOW_STOCK_THRESHOLD = 10;

    @Override
    public void onStockChanged(String productName, int oldLevel, int newLevel) {
        if (newLevel < LOW_STOCK_THRESHOLD) {
            System.out.println("  [SMS] Alert: " + productName + " low stock: " + newLevel + " units");
        }
    }
}

class DashboardObserver implements StockObserver {
    @Override
    public void onStockChanged(String productName, int oldLevel, int newLevel) {
        System.out.println("  [DASHBOARD] Stock update: " + productName + " - " + newLevel + " units");

        if (newLevel < 10) {
            System.out.println("  [DASHBOARD] Warning: " + productName + " - " + newLevel + " units remaining");
        }
    }
}

class SlackObserver implements StockObserver {
    @Override
    public void onStockChanged(String productName, int oldLevel, int newLevel) {
        if (newLevel < 5) {
            System.out.println("  [SLACK] Critical: " + productName + " critically low (" + newLevel + " units) @warehouse-team");
        }
    }
}

class AnalyticsObserver implements StockObserver {
    @Override
    public void onStockChanged(String productName, int oldLevel, int newLevel) {
        int change = newLevel - oldLevel;
        System.out.println("  [ANALYTICS] Logged: " + productName + " stock change: " +
                (change > 0 ? "+" : "") + change + " units");
    }
}

