package extractcomposite;

// AFTER REFACTORING
// Solution: Extract common Composite structure into reusable base classes

import java.util.ArrayList;
import java.util.List;

class ExtractCompositeAfter {
    public static void main(String[] args) {
        // Product hierarchy
        ProductCategoryAfter electronics = new ProductCategoryAfter("Electronics");
        electronics.add(new ProductAfter("Laptop", 999.99));
        electronics.add(new ProductAfter("Mouse", 29.99));

        // Can now nest categories!
        ProductCategoryAfter computers = new ProductCategoryAfter("Computers");
        computers.add(new ProductAfter("Desktop", 1299.99));
        electronics.add(computers);

        System.out.println("Electronics total: $" + electronics.getTotalPrice());
        electronics.displayInfo();

        System.out.println();

        DepartmentGroupAfter engineering = new DepartmentGroupAfter("Engineering");
        engineering.add(new DepartmentAfter("Backend", 15));
        engineering.add(new DepartmentAfter("Frontend", 12));

        DepartmentGroupAfter webTeam = new DepartmentGroupAfter("Web Team");
        webTeam.add(new DepartmentAfter("UI/UX", 8));
        engineering.add(webTeam);

        System.out.println("Total employees: " + engineering.getTotalEmployees());
        engineering.displayInfo();

        printComponentCount(electronics);
        printComponentCount(engineering);
    }

    private static void printComponentCount(CompositeComponent composite) {
        System.out.println("\n" + composite.getName() + " has " +
                composite.getChildren().size() + " direct children");
    }
}

// Abstract Component - base for both leaf and composite
abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void displayInfo();
}

abstract class CompositeComponent extends Component {
    protected List<Component> children;

    public CompositeComponent(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    public Component getChild(int index) {
        return children.get(index);
    }

    public List<Component> getChildren() {
        return new ArrayList<>(children);
    }

    protected void displayChildren() {
        for (Component child : children) {
            child.displayInfo();
        }
    }
}

class ProductCategoryAfter extends CompositeComponent {
    public ProductCategoryAfter(String name) {
        super(name);
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Component child : children) {
            if (child instanceof ProductAfter) {
                total += ((ProductAfter) child).getPrice();
            } else if (child instanceof ProductCategoryAfter) {
                total += ((ProductCategoryAfter) child).getTotalPrice();
            }
        }
        return total;
    }

    @Override
    public void displayInfo() {
        System.out.println("Category: " + name);
        displayChildren();
    }
}

class ProductAfter extends Component {
    private final double price;

    public ProductAfter(String name, double price) {
        super(name);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void displayInfo() {
        System.out.println("  - " + name + ": $" + price);
    }
}

class DepartmentGroupAfter extends CompositeComponent {
    public DepartmentGroupAfter(String name) {
        super(name);
    }

    public int getTotalEmployees() {
        int total = 0;
        for (Component child : children) {
            if (child instanceof DepartmentAfter) {
                total += ((DepartmentAfter) child).getEmployeeCount();
            } else if (child instanceof DepartmentGroupAfter) {
                total += ((DepartmentGroupAfter) child).getTotalEmployees();
            }
        }
        return total;
    }

    @Override
    public void displayInfo() {
        System.out.println("Department Group: " + name);
        displayChildren();
    }
}

class DepartmentAfter extends Component {
    private final int employeeCount;

    public DepartmentAfter(String name, int employeeCount) {
        super(name);
        this.employeeCount = employeeCount;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    @Override
    public void displayInfo() {
        System.out.println("  - " + name + ": " + employeeCount + " employees");
    }
}
