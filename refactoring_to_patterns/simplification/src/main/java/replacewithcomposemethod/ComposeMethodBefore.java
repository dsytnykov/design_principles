package replacewithcomposemethod;

// BEFORE REFACTORING
// Problem: Long method with mixed abstraction levels and unclear intent


import java.util.ArrayList;
import java.util.List;

public class ComposeMethodBefore {
    private final List<Item> items = new ArrayList<>();

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
        System.out.println("===== CHECKOUT =====");
        System.out.println();

        // Calculate subtotal
        double subtotal = 0.0;
        for (Item item : items) {
            double itemTotal = item.price * item.quantity;
            subtotal += itemTotal;
            System.out.println(item.name + " x" + item.quantity +
                    " @ $" + item.price + " = $" + itemTotal);
        }
        System.out.println();
        System.out.println("Subtotal: $" + String.format("%.2f", subtotal));

        // Apply membership discount
        double discount = 0.0;
        if (hasMembership) {
            discount = subtotal * 0.10;
            System.out.println("Membership Discount (10%): -$" +
                    String.format("%.2f", discount));
        }

        // Apply coupon
        double couponDiscount = 0.0;
        if (couponCode != null && !couponCode.isEmpty()) {
            if (couponCode.equals("SAVE20")) {
                couponDiscount = subtotal * 0.20;
            } else if (couponCode.equals("SAVE10")) {
                couponDiscount = subtotal * 0.10;
            } else if (couponCode.equals("FLAT5")) {
                couponDiscount = 5.0;
            }
            if (couponDiscount > 0) {
                System.out.println("Coupon (" + couponCode + "): -$" +
                        String.format("%.2f", couponDiscount));
            }
        }

        double totalAfterDiscounts = subtotal - discount - couponDiscount;

        // Calculate tax
        double tax = 0.0;
        for (Item item : items) {
            if (item.taxable) {
                tax += (item.price * item.quantity) * 0.08;
            }
        }

        // Adjust tax for discounts proportionally
        if (subtotal > 0) {
            double discountRatio = totalAfterDiscounts / subtotal;
            tax = tax * discountRatio;
        }

        System.out.println("Tax (8%): $" + String.format("%.2f", tax));

        double total = totalAfterDiscounts + tax;

        System.out.println();
        System.out.println("TOTAL: $" + String.format("%.2f", total));
        System.out.println("====================");
    }

    public static void main(String[] args) {
        ComposeMethodBefore cart = new ComposeMethodBefore(); // ShoppingCart
        cart.addItem("Laptop", 999.99, 1, true);
        cart.addItem("Mouse", 29.99, 2, true);
        cart.addItem("Book", 19.99, 3, false);

        cart.checkout(true, "SAVE20");
    }
}

