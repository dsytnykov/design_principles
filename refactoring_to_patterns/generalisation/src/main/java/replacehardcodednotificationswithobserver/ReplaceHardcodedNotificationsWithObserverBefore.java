package replacehardcodednotificationswithobserver;

// BEFORE REFACTORING
// Problem: Notifications are hard-coded, making the system inflexible and tightly coupled

public class ReplaceHardcodedNotificationsWithObserverBefore {
    public static void main(String[] args) {
        InventorySystem inventory = new InventorySystem("Laptop", 50);

        inventory.removeStock(10);
        inventory.removeStock(35);  // Triggers low stock alerts
        inventory.addStock(20);

        // Problem: Can't easily add new notification types (e.g., Slack, webhook)
        // without modifying InventorySystem class
    }
}


class InventorySystem {
    private int stockLevel;
    private String productName;

    private EmailNotifier emailNotifier;
    private SMSNotifier smsNotifier;
    private DashboardNotifier dashboardNotifier;

    public InventorySystem(String productName, int initialStock) {
        this.productName = productName;
        this.stockLevel = initialStock;

        // Must create all notifiers even if not all are needed
        this.emailNotifier = new EmailNotifier();
        this.smsNotifier = new SMSNotifier();
        this.dashboardNotifier = new DashboardNotifier();
    }

    public void removeStock(int quantity) {
        stockLevel -= quantity;
        System.out.println("Removed " + quantity + " units. New stock: " + stockLevel);

        // Hard-coded notification logic - must modify this method to add new notifications
        if (stockLevel < 10) {
            emailNotifier.sendLowStockEmail(productName, stockLevel);
            smsNotifier.sendLowStockSMS(productName, stockLevel);
            dashboardNotifier.updateDashboardAlert(productName, stockLevel);
        }
    }

    public void addStock(int quantity) {
        stockLevel += quantity;
        System.out.println("Added " + quantity + " units. New stock: " + stockLevel);

        // More hard-coded notifications
        dashboardNotifier.updateDashboardStock(productName, stockLevel);
    }

    public int getStockLevel() {
        return stockLevel;
    }
}

class EmailNotifier {
    public void sendLowStockEmail(String product, int level) {
        System.out.println("  [EMAIL] Alert: " + product + " is low on stock (" + level + " units)");
    }
}

class SMSNotifier {
    public void sendLowStockSMS(String product, int level) {
        System.out.println("  [SMS] Alert: " + product + " low stock: " + level + " units");
    }
}

class DashboardNotifier {
    public void updateDashboardAlert(String product, int level) {
        System.out.println("  [DASHBOARD] Warning: " + product + " - " + level + " units remaining");
    }

    public void updateDashboardStock(String product, int level) {
        System.out.println("  [DASHBOARD] Stock update: " + product + " - " + level + " units");
    }
}
