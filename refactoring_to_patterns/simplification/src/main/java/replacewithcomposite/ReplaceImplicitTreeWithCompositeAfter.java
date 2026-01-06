package replacewithcomposite;

// AFTER REFACTORING
// Solution: Explicit Composite pattern with clear component hierarchy

import java.util.*;

interface OrderComponent {
    double calculateTotal();
    void printOrder(int indent);
    String getName();
}

// Leaf class - represents individual products
class ProductOrder implements OrderComponent {
    private final String productName;
    private final double price;

    public ProductOrder(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    @Override
    public double calculateTotal() {
        return price;
    }

    @Override
    public void printOrder(int indent) {
        String indentStr = "  ".repeat(indent);
        System.out.println(indentStr + productName + ": $" + price);
    }

    @Override
    public String getName() {
        return productName;
    }
}

// Composite class - represents groups of orders
class CompositeOrder implements OrderComponent {
    private final String name;
    private final List<OrderComponent> children;

    public CompositeOrder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void add(OrderComponent component) {
        children.add(component);
    }

    public void remove(OrderComponent component) {
        children.remove(component);
    }

    public OrderComponent getChild(int index) {
        return children.get(index);
    }

    @Override
    public double calculateTotal() {
        double total = 0.0;
        for (OrderComponent child : children) {
            total += child.calculateTotal();
        }
        return total;
    }

    @Override
    public void printOrder(int indent) {
        String indentStr = "  ".repeat(indent);
        System.out.println(indentStr + name + ":");
        for (OrderComponent child : children) {
            child.printOrder(indent + 1);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public int getChildCount() {
        return children.size();
    }
}

class ReplaceImplicitTreeWithCompositeAfter {
    public static void main(String[] args) {
        CompositeOrder computerBundle = new CompositeOrder("Computer Bundle");

        CompositeOrder computer = new CompositeOrder("Desktop Computer");
        computer.add(new ProductOrder("CPU", 299.99));
        computer.add(new ProductOrder("RAM", 89.99));
        computer.add(new ProductOrder("Hard Drive", 129.99));

        CompositeOrder peripherals = new CompositeOrder("Peripherals");
        peripherals.add(new ProductOrder("Monitor", 249.99));
        peripherals.add(new ProductOrder("Keyboard", 79.99));
        peripherals.add(new ProductOrder("Mouse", 29.99));

        computerBundle.add(computer);
        computerBundle.add(peripherals);
        computerBundle.add(new ProductOrder("Warranty", 99.99));

        System.out.println("Order Details:");
        computerBundle.printOrder(0);
        System.out.println("\nTotal: $" + computerBundle.calculateTotal());

        printComponentInfo(computer);
        printComponentInfo(peripherals);
    }

    private static void printComponentInfo(OrderComponent component) {
        System.out.println("\n" + component.getName() +
                " subtotal: $" + component.calculateTotal());
    }
}
