package replaceonemanydistinctions;

// AFTER REFACTORING
// Solution: Use Composite pattern - treat single and multiple uniformly

import java.util.*;

public class ReplaceOneManyDistinctionsAfter {
    public static void main(String[] args) {
        // Single color product - treated as collection with one element
        ProductSpecificationAfter basicShirt = new ProductSpecificationAfter("Blue");
        System.out.println(basicShirt.getDisplayText());
        System.out.println("Matches Red? " + basicShirt.matchesColor("Red"));
        System.out.println("Matches Blue? " + basicShirt.matchesColor("Blue"));
        System.out.println("Color count: " + basicShirt.getColorCount());

        // Multiple color product - same interface
        ProductSpecificationAfter fancyShirt = new ProductSpecificationAfter(
                Arrays.asList("Red", "Blue", "Green")
        );
        System.out.println("\n" + fancyShirt.getDisplayText());
        System.out.println("Matches Red? " + fancyShirt.matchesColor("Red"));
        System.out.println("Color count: " + fancyShirt.getColorCount());

        // Single shipping method - treated uniformly
        ShippingOptionAfter basicShipping = new ShippingOptionAfter("Standard");
        System.out.println("\n" + basicShipping.getDisplayText());
        System.out.println("Cost: $" + basicShipping.calculateCost("Standard"));
        System.out.println("Method count: " + basicShipping.getMethodCount());

        // Multiple shipping methods - same interface
        ShippingOptionAfter flexibleShipping = new ShippingOptionAfter(
                Arrays.asList("Standard", "Express", "Overnight")
        );
        System.out.println("\n" + flexibleShipping.getDisplayText());
        System.out.println("Express cost: $" + flexibleShipping.calculateCost("Express"));
        System.out.println("Cheapest: $" + flexibleShipping.getCheapestCost());
        System.out.println("Method count: " + flexibleShipping.getMethodCount());

        // Demonstrate uniform handling
        processSpecification(basicShirt);
        processSpecification(fancyShirt);
    }

    private static void processSpecification(ProductSpecificationAfter spec) {
        System.out.println("\nProcessing specification with " +
                spec.getColorCount() + " color(s)");
    }
}


class ProductSpecificationAfter {
    private List<String> colors;

    public ProductSpecificationAfter(String color) {
        this.colors = new ArrayList<>();
        this.colors.add(color);
    }

    public ProductSpecificationAfter(List<String> colors) {
        this.colors = new ArrayList<>(colors);
    }

    public boolean matchesColor(String colorToMatch) {
        for (String c : colors) {
            if (c.equalsIgnoreCase(colorToMatch)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getColors() {
        return new ArrayList<>(colors);
    }

    public String getDisplayText() {
        return "Available in: " + String.join(", ", colors);
    }

    public int getColorCount() {
        return colors.size();
    }

    public boolean hasColor(String color) {
        return matchesColor(color);
    }

    public void addColor(String color) {
        if (!colors.contains(color.toLowerCase())) {
            colors.add(color);
        }
    }
}

class ShippingOptionAfter {
    private List<String> methods;

    public ShippingOptionAfter(String method) {
        this.methods = new ArrayList<>();
        this.methods.add(method);
    }

    public ShippingOptionAfter(List<String> methods) {
        this.methods = new ArrayList<>(methods);
    }

    // No more conditional logic
    public boolean supportsMethod(String methodToCheck) {
        for (String m : methods) {
            if (m.equalsIgnoreCase(methodToCheck)) {
                return true;
            }
        }
        return false;
    }

    public double calculateCost(String selectedMethod) {
        for (String m : methods) {
            if (m.equalsIgnoreCase(selectedMethod)) {
                return getCostForMethod(m);
            }
        }
        return 0.0;
    }

    private double getCostForMethod(String method) {
        switch (method.toLowerCase()) {
            case "standard": return 5.99;
            case "express": return 12.99;
            case "overnight": return 24.99;
            default: return 0.0;
        }
    }

    public String getDisplayText() {
        return "Shipping: " + String.join(", ", methods);
    }

    public List<String> getMethods() {
        return new ArrayList<>(methods);
    }

    public int getMethodCount() {
        return methods.size();
    }

    public double getCheapestCost() {
        double minCost = Double.MAX_VALUE;
        for (String method : methods) {
            double cost = getCostForMethod(method);
            if (cost < minCost) {
                minCost = cost;
            }
        }
        return minCost;
    }
}
