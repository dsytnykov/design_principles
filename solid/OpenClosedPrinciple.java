
public class OpenClosedPrinciple {
}

//Wrong
/*public class DiscountCalculator {
    public double calculateDiscount(String customerType, double amount) {
        if (customerType.equals("Regular")) {
            return amount * 0.1;
        } else if (customerType.equals("Premium")) {
            return amount * 0.2;
        }
        return 0;
    }

    public static void main(String[] args) {
        DiscountCalculator calculator = new DiscountCalculator();
        double regularDiscount = calculator.calculateDiscount("Regular", 100);
        double premiumDiscount = calculator.calculateDiscount("Premium", 100);

        System.out.println("Regular Discount: " + regularDiscount);
        System.out.println("Premium Discount: " + premiumDiscount);
    }
}*/

//Correct
// Discount.java
/*
public abstract class Discount {
    public abstract double calculate(double amount);
}

// RegularDiscount.java
public class RegularDiscount extends Discount {
    public double calculate(double amount) {
        return amount * 0.1;
    }
}

// PremiumDiscount.java
public class PremiumDiscount extends Discount {
    public double calculate(double amount) {
        return amount * 0.2;
    }
}

// DiscountCalculator.java
public class DiscountCalculator {
    public double calculateDiscount(Discount discount, double amount) {
        return discount.calculate(amount);
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Discount regularDiscount = new RegularDiscount();
        Discount premiumDiscount = new PremiumDiscount();

        DiscountCalculator calculator = new DiscountCalculator();

        double regularDiscountAmount = calculator.calculateDiscount(regularDiscount, 100);
        double premiumDiscountAmount = calculator.calculateDiscount(premiumDiscount, 100);

        System.out.println("Regular Discount: " + regularDiscountAmount);
        System.out.println("Premium Discount: " + premiumDiscountAmount);
    }
}*/
