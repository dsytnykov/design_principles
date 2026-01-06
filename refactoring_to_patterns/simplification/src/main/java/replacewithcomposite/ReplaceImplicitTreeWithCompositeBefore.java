package replacewithcomposite;

// BEFORE REFACTORING
// Problem: Tree structure is implicit and hard to work with

import java.util.*;

class Order {
    private final String productName;
    private final double price;
    private final List<Order> subOrders; // Implicit tree structure

    public Order(String productName, double price) {
        this.productName = productName;
        this.price = price;
        this.subOrders = new ArrayList<>();
    }

    public Order(String productName) {
        this.productName = productName;
        this.price = 0.0;
        this.subOrders = new ArrayList<>();
    }

    public void addSubOrder(Order order) {
        subOrders.add(order);
    }

    public boolean hasSubOrders() {
        return !subOrders.isEmpty();
    }

    // Complex calculation logic mixing leaf and composite behavior
    public double calculateTotal() {
        if (hasSubOrders()) {
            // Composite: sum up sub-orders
            double total = 0.0;
            for (Order subOrder : subOrders) {
                total += subOrder.calculateTotal();
            }
            return total;
        } else {
            // Leaf: return own price
            return price;
        }
    }

    public void printOrder(int indent) {
        String indentStr = "  ".repeat(indent);

        if (hasSubOrders()) {
            System.out.println(indentStr + productName + ":");
            for (Order subOrder : subOrders) {
                subOrder.printOrder(indent + 1);
            }
        } else {
            System.out.println(indentStr + productName + ": $" + price);
        }
    }

    public String getProductName() {
        return productName;
    }
}

class ReplaceImplicitTreeWithCompositeBefore {
    public static void main(String[] args) {
        Order computerBundle = new Order("Computer Bundle");

        Order computer = new Order("Desktop Computer");
        computer.addSubOrder(new Order("CPU", 299.99));
        computer.addSubOrder(new Order("RAM", 89.99));
        computer.addSubOrder(new Order("Hard Drive", 129.99));

        Order peripherals = new Order("Peripherals");
        peripherals.addSubOrder(new Order("Monitor", 249.99));
        peripherals.addSubOrder(new Order("Keyboard", 79.99));
        peripherals.addSubOrder(new Order("Mouse", 29.99));

        computerBundle.addSubOrder(computer);
        computerBundle.addSubOrder(peripherals);
        computerBundle.addSubOrder(new Order("Warranty", 99.99));

        System.out.println("Order Details:");
        computerBundle.printOrder(0);
        System.out.println("\nTotal: $" + computerBundle.calculateTotal());
    }
}
