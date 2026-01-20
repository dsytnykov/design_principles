package replacewithstrategy;

// AFTER REFACTORING
// Solution: Strategy pattern encapsulates each algorithm

public class ReplaceConditionalLogicWithStrategyAfter {
    public static void main(String[] args) {
        double weight = 15.0;
        double distance = 600.0;

        // Can easily switch between strategies
        ShippingCostCalculatorAfter calculator = new ShippingCostCalculatorAfter(new StandardShipping());
        printShippingInfo(calculator, weight, distance);

        calculator.setStrategy(new ExpressShipping());
        printShippingInfo(calculator, weight, distance);

        calculator.setStrategy(new OvernightShipping());
        printShippingInfo(calculator, weight, distance);

        calculator.setStrategy(new InternationalShipping());
        printShippingInfo(calculator, weight, distance);

        // Can also compare strategies easily
        System.out.println("\n--- Comparing all options ---");
        compareShippingOptions(weight, distance);
    }

    private static void printShippingInfo(ShippingCostCalculatorAfter calculator,
                                          double weight, double distance) {
        System.out.println("\n" + calculator.getStrategyName() + " shipping:");
        System.out.println("Cost: $" + String.format("%.2f",
                calculator.calculateCost(weight, distance)));
        System.out.println("Delivery: " + calculator.estimateDeliveryDays(distance) + " days");
    }

    private static void compareShippingOptions(double weight, double distance) {
        ShippingStrategy[] strategies = {
                new StandardShipping(),
                new ExpressShipping(),
                new OvernightShipping(),
                new InternationalShipping()
        };

        for (ShippingStrategy strategy : strategies) {
            ShippingCostCalculatorAfter calc = new ShippingCostCalculatorAfter(strategy);
            System.out.println(strategy.getName() + ": $" +
                    String.format("%.2f", calc.calculateCost(weight, distance)) +
                    " (" + calc.estimateDeliveryDays(distance) + " days)");
        }
    }
}

interface ShippingStrategy {
    double calculateCost(double weight, double distance);

    int estimateDeliveryDays(double distance);

    String getName();
}

class StandardShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        double cost = 5.0 + (weight * 0.5);
        if (distance > 500) {
            cost += distance * 0.01;
        }
        return cost;
    }

    @Override
    public int estimateDeliveryDays(double distance) {
        return distance < 500 ? 5 : 7;
    }

    @Override
    public String getName() {
        return "Standard";
    }
}

class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        double cost = 15.0 + (weight * 0.8) + (distance * 0.05);
        if (weight > 20) {
            cost += (weight - 20) * 0.5; // Heavy package surcharge
        }
        return cost;
    }

    @Override
    public int estimateDeliveryDays(double distance) {
        return distance < 500 ? 2 : 3;
    }

    @Override
    public String getName() {
        return "Express";
    }
}

class OvernightShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        double cost = 25.0 + (weight * 1.2) + (distance * 0.1);
        if (distance > 1000) {
            cost *= 1.3; // Long distance multiplier
        }
        return cost;
    }

    @Override
    public int estimateDeliveryDays(double distance) {
        return 1;
    }

    @Override
    public String getName() {
        return "Overnight";
    }
}

class InternationalShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        double cost = 40.0 + (weight * 2.0);
        if (weight > 10) {
            cost += 20.0; // Heavy package customs fee
        }
        cost += distance * 0.15; // International distance fee
        return cost;
    }

    @Override
    public int estimateDeliveryDays(double distance) {
        return distance < 2000 ? 10 : 14;
    }

    @Override
    public String getName() {
        return "International";
    }
}

class ShippingCostCalculatorAfter {
    private ShippingStrategy strategy;

    public ShippingCostCalculatorAfter(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateCost(double weight, double distance) {
        return strategy.calculateCost(weight, distance);
    }

    public int estimateDeliveryDays(double distance) {
        return strategy.estimateDeliveryDays(distance);
    }

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public String getStrategyName() {
        return strategy.getName();
    }
}

