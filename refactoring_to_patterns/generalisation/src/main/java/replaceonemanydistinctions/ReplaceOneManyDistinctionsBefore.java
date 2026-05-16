package replaceonemanydistinctions;

// BEFORE REFACTORING
// Problem: Special handling for single vs. multiple items throughout the code

import java.util.*;

public class ReplaceOneManyDistinctionsBefore {
    public static void main(String[] args) {
        // Single color product
        ProductSpecification basicShirt = new ProductSpecification("Blue");
        System.out.println(basicShirt.getDisplayText());
        System.out.println("Matches Red? " + basicShirt.matchesColor("Red"));
        System.out.println("Matches Blue? " + basicShirt.matchesColor("Blue"));

        // Multiple color product
        ProductSpecification fancyShirt = new ProductSpecification(
                Arrays.asList("Red", "Blue", "Green")
        );
        System.out.println("\n" + fancyShirt.getDisplayText());
        System.out.println("Matches Red? " + fancyShirt.matchesColor("Red"));

        // Single shipping method
        ShippingOption basicShipping = new ShippingOption("Standard");
        System.out.println("\n" + basicShipping.getDisplayText());
        System.out.println("Cost: $" + basicShipping.calculateCost("Standard"));

        // Multiple shipping methods
        ShippingOption flexibleShipping = new ShippingOption(
                Arrays.asList("Standard", "Express", "Overnight")
        );
        System.out.println("\n" + flexibleShipping.getDisplayText());
        System.out.println("Express cost: $" + flexibleShipping.calculateCost("Express"));
    }
}


class ProductSpecification {
    private final String color;
    private final List<String> colors;
    private final boolean isSingleColor;

    public ProductSpecification(String color) {
        this.color = color;
        this.isSingleColor = true;
        this.colors = null;
    }

    public ProductSpecification(List<String> colors) {
        this.colors = new ArrayList<>(colors);
        this.isSingleColor = false;
        this.color = null;
    }

    public boolean matchesColor(String colorToMatch) {
        if (isSingleColor) {
            return color.equalsIgnoreCase(colorToMatch);
        } else {
            for (String c : colors) {
                if (c.equalsIgnoreCase(colorToMatch)) {
                    return true;
                }
            }
            return false;
        }
    }

    public List<String> getColors() {
        if (isSingleColor) {
            List<String> result = new ArrayList<>();
            result.add(color);
            return result;
        } else {
            return new ArrayList<>(colors);
        }
    }

    public String getDisplayText() {
        if (isSingleColor) {
            return "Available in: " + color;
        } else {
            return "Available in: " + String.join(", ", colors);
        }
    }

    public int getColorCount() {
        if (isSingleColor) {
            return 1;
        } else {
            return colors.size();
        }
    }
}

class ShippingOption {
    private final String method;
    private final List<String> methods;
    private final boolean isSingleMethod;

    public ShippingOption(String method) {
        this.method = method;
        this.isSingleMethod = true;
        this.methods = null;
    }

    public ShippingOption(List<String> methods) {
        this.methods = new ArrayList<>(methods);
        this.isSingleMethod = false;
        this.method = null;
    }

    public boolean supportsMethod(String methodToCheck) {
        if (isSingleMethod) {
            return method.equalsIgnoreCase(methodToCheck);
        } else {
            for (String m : methods) {
                if (m.equalsIgnoreCase(methodToCheck)) {
                    return true;
                }
            }
            return false;
        }
    }

    public double calculateCost(String selectedMethod) {
        if (isSingleMethod) {
            if (method.equalsIgnoreCase(selectedMethod)) {
                return getCostForMethod(method);
            }
            return 0.0;
        } else {
            for (String m : methods) {
                if (m.equalsIgnoreCase(selectedMethod)) {
                    return getCostForMethod(m);
                }
            }
            return 0.0;
        }
    }

    private double getCostForMethod(String method) {
        return switch (method.toLowerCase()) {
            case "standard" -> 5.99;
            case "express" -> 12.99;
            case "overnight" -> 24.99;
            default -> 0.0;
        };
    }

    public String getDisplayText() {
        if (isSingleMethod) {
            return "Shipping: " + method;
        } else {
            return "Shipping: " + String.join(", ", methods);
        }
    }
}
