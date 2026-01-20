package replacewithstrategy;

// BEFORE REFACTORING
// Problem: Complex conditional logic for different calculation algorithms

public class ReplaceConditionalLogicWithStrategyBefore {
    public static void main(String[] args) {
        ShippingCostCalculator calculator = new ShippingCostCalculator("STANDARD");

        double weight = 15.0;
        double distance = 600.0;

        System.out.println("Standard shipping:");
        System.out.println("Cost: $" + calculator.calculateCost(weight, distance));
        System.out.println("Delivery: " + calculator.estimateDeliveryDays(distance) + " days");

        calculator.setShippingType("EXPRESS");
        System.out.println("\nExpress shipping:");
        System.out.println("Cost: $" + calculator.calculateCost(weight, distance));
        System.out.println("Delivery: " + calculator.estimateDeliveryDays(distance) + " days");

        calculator.setShippingType("OVERNIGHT");
        System.out.println("\nOvernight shipping:");
        System.out.println("Cost: $" + calculator.calculateCost(weight, distance));
        System.out.println("Delivery: " + calculator.estimateDeliveryDays(distance) + " days");
    }
}

class ShippingCostCalculator {
    private static final String STANDARD = "STANDARD";
    private static final String EXPRESS = "EXPRESS";
    private static final String OVERNIGHT = "OVERNIGHT";
    private static final String INTERNATIONAL = "INTERNATIONAL";

    private String shippingType;

    public ShippingCostCalculator(String shippingType) {
        this.shippingType = shippingType;
    }

    public double calculateCost(double weight, double distance) {
        double cost = 0.0;

        if (shippingType.equals(STANDARD)) {
            cost = 5.0 + (weight * 0.5);
            if (distance > 500) {
                cost += distance * 0.01;
            }
        } else if (shippingType.equals(EXPRESS)) {
            cost = 15.0 + (weight * 0.8) + (distance * 0.05);
            if (weight > 20) {
                cost += (weight - 20) * 0.5; // Heavy package surcharge
            }
        } else if (shippingType.equals(OVERNIGHT)) {
            cost = 25.0 + (weight * 1.2) + (distance * 0.1);
            if (distance > 1000) {
                cost *= 1.3; // Long distance multiplier
            }
        } else if (shippingType.equals(INTERNATIONAL)) {
            cost = 40.0 + (weight * 2.0);
            if (weight > 10) {
                cost += 20.0; // Heavy package customs fee
            }
            cost += distance * 0.15; // International distance fee
        } else {
            throw new IllegalArgumentException("Unknown shipping type: " + shippingType);
        }

        return cost;
    }

    public int estimateDeliveryDays(double distance) {
        int days = 0;

        if (shippingType.equals(STANDARD)) {
            days = distance < 500 ? 5 : 7;
        } else if (shippingType.equals(EXPRESS)) {
            days = distance < 500 ? 2 : 3;
        } else if (shippingType.equals(OVERNIGHT)) {
            days = 1;
        } else if (shippingType.equals(INTERNATIONAL)) {
            days = distance < 2000 ? 10 : 14;
        }

        return days;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }
}

