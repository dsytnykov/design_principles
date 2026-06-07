package unifyinterfaceswithadapter;

class ThirdPartyPaymentService {
    public int charge(String customerId, String itemDescription, double price) {
        System.out.println("Charging via third-party service...");
        System.out.println("Customer: " + customerId + ", Item: " + itemDescription + ", Price: $" + price);
        return 200; // HTTP status code
    }
}
