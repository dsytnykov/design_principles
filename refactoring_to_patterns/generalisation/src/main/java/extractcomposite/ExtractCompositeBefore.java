package extractcomposite;

// BEFORE REFACTORING
// Problem: Multiple classes have similar parent-child collection handling
// but no common abstraction

import java.util.ArrayList;
import java.util.List;

class ExtractCompositeBefore {
    public static void main(String[] args) {
        ProductCategory electronics = new ProductCategory("Electronics");
        electronics.addProduct(new Product("Laptop", 999.99));
        electronics.addProduct(new Product("Mouse", 29.99));
        System.out.println("Electronics total: $" + electronics.getTotalPrice());
        electronics.displayInfo();

        System.out.println();

        DepartmentGroup engineering = new DepartmentGroup("Engineering");
        engineering.addDepartment(new Department("Backend", 15));
        engineering.addDepartment(new Department("Frontend", 12));
        System.out.println("Total employees: " + engineering.getTotalEmployees());
        engineering.displayInfo();
    }
}

class ProductCategory {
    private final String name;
    private final List<Product> products;

    public ProductCategory(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void displayInfo() {
        System.out.println("Category: " + name);
        for (Product product : products) {
            product.displayInfo();
        }
    }
}

class Product {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void displayInfo() {
        System.out.println("  - " + name + ": $" + price);
    }
}

class DepartmentGroup {
    private final String name;
    private final List<Department> departments;

    public DepartmentGroup(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }

    public int getTotalEmployees() {
        int total = 0;
        for (Department department : departments) {
            total += department.getEmployeeCount();
        }
        return total;
    }

    public void displayInfo() {
        System.out.println("Department Group: " + name);
        for (Department department : departments) {
            department.displayInfo();
        }
    }
}

class Department {
    private final String name;
    private final int employeeCount;

    public Department(String name, int employeeCount) {
        this.name = name;
        this.employeeCount = employeeCount;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void displayInfo() {
        System.out.println("  - " + name + ": " + employeeCount + " employees");
    }
}

