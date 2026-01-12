package replacewithcomposemethod;

// AFTER REFACTORING
// Solution: Method composed of smaller methods at same abstraction level

import java.util.*;

class ComposeMethodAfter {
    private final List<Item> items = new ArrayList<>();
    private static final double MEMBERSHIP_DISCOUNT_RATE = 0.10;
    private static final double TAX_RATE = 0.08;

    static class Item {
        String name;
        double price;
        int quantity;
        boolean taxable;

        Item(String name, double price, int quantity, boolean taxable) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.taxable = taxable;
        }
    }

    public void addItem(String name, double price, int quantity, boolean taxable) {
        items.add(new Item(name, price, quantity, taxable));
    }

    public void checkout(boolean hasMembership, String couponCode) {
        printHeader();

        double subtotal = calculateAndPrintSubtotal();
        double discount = applyMembershipDiscount(subtotal, hasMembership);
        double couponDiscount = applyCouponDiscount(subtotal, couponCode);
        double totalAfterDiscounts = subtotal - discount - couponDiscount;

        double tax = calculateAdjustedTax(totalAfterDiscounts, subtotal);
        printTax(tax);

        double total = totalAfterDiscounts + tax;
        printTotal(total);

        printFooter();
    }

    private void printHeader() {
        System.out.println("===== CHECKOUT =====");
        System.out.println();
    }

    private double calculateAndPrintSubtotal() {
        double subtotal = 0.0;

        for (Item item : items) {
            double itemTotal = calculateItemTotal(item);
            subtotal += itemTotal;
            printItemLine(item, itemTotal);
        }

        System.out.println();
        System.out.println("Subtotal: $" + formatMoney(subtotal));

        return subtotal;
    }

    private double calculateItemTotal(Item item) {
        return item.price * item.quantity;
    }

    private void printItemLine(Item item, double itemTotal) {
        System.out.println(item.name + " x" + item.quantity +
                " @ $" + item.price + " = $" + itemTotal);
    }

    private double applyMembershipDiscount(double subtotal, boolean hasMembership) {
        if (!hasMembership) {
            return 0.0;
        }

        double discount = subtotal * MEMBERSHIP_DISCOUNT_RATE;
        System.out.println("Membership Discount (10%): -$" + formatMoney(discount));
        return discount;
    }

    private double applyCouponDiscount(double subtotal, String couponCode) {
        double couponDiscount = calculateCouponDiscount(subtotal, couponCode);

        if (couponDiscount > 0) {
            System.out.println("Coupon (" + couponCode + "): -$" +
                    formatMoney(couponDiscount));
        }

        return couponDiscount;
    }

    private double calculateCouponDiscount(double subtotal, String couponCode) {
        if (couponCode == null || couponCode.isEmpty()) {
            return 0.0;
        }

        return switch (couponCode) {
            case "SAVE20" -> subtotal * 0.20;
            case "SAVE10" -> subtotal * 0.10;
            case "FLAT5" -> 5.0;
            default -> 0.0;
        };
    }

    private double calculateAdjustedTax(double totalAfterDiscounts, double subtotal) {
        double baseTax = calculateBaseTax();
        return adjustTaxForDiscounts(baseTax, totalAfterDiscounts, subtotal);
    }

    private double calculateBaseTax() {
        double tax = 0.0;
        for (Item item : items) {
            if (item.taxable) {
                tax += calculateItemTotal(item) * TAX_RATE;
            }
        }
        return tax;
    }

    private double adjustTaxForDiscounts(double baseTax, double totalAfterDiscounts,
                                         double subtotal) {
        if (subtotal == 0) {
            return 0.0;
        }

        double discountRatio = totalAfterDiscounts / subtotal;
        return baseTax * discountRatio;
    }

    private void printTax(double tax) {
        System.out.println("Tax (8%): $" + formatMoney(tax));
    }

    private void printTotal(double total) {
        System.out.println();
        System.out.println("TOTAL: $" + formatMoney(total));
    }

    private void printFooter() {
        System.out.println("====================");
    }

    private String formatMoney(double amount) {
        return String.format("%.2f", amount);
    }

    public static void main(String[] args) {
        ComposeMethodAfter cart = new ComposeMethodAfter(); // Shopping Cart
        cart.addItem("Laptop", 999.99, 1, true);
        cart.addItem("Mouse", 29.99, 2, true);
        cart.addItem("Book", 19.99, 3, false);

        cart.checkout(true, "SAVE20");
    }
}
