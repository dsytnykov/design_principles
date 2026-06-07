package unifyinterfaceswithadapter;

// BEFORE REFACTORING
// Problem: Different payment processors have incompatible interfaces

public class UnifyInterfacesWithAdapterBefore {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.processPayment("OLD", "ACC-789", 99.99);
        processor.processPayment("NEW", "TOK-456", 149.50);
        processor.processPayment("THIRD_PARTY", "CUST-123", 199.99);
    }
}

// Client code has to deal with different interfaces
class PaymentProcessor {
    private final OldPaymentSystem oldSystem;
    private final NewPaymentGateway newGateway;
    private final ThirdPartyPaymentService thirdParty;

    public PaymentProcessor() {
        this.oldSystem = new OldPaymentSystem();
        this.newGateway = new NewPaymentGateway();
        this.thirdParty = new ThirdPartyPaymentService();
    }

    public void processPayment(String paymentType, String identifier, double amount) {
        // Complex conditional logic to handle different interfaces
        switch (paymentType) {
            case "OLD" -> {
                boolean success = oldSystem.makePayment(identifier, amount);
                if (success) {
                    System.out.println("Old system payment successful\n");
                }
            }
            case "NEW" -> {
                int amountInCents = (int) (amount * 100);
                String txnId = newGateway.processTransaction(identifier, amountInCents, "USD");
                boolean verified = newGateway.verifyTransaction(txnId);
                if (verified) {
                    System.out.println("New gateway payment successful\n");
                }
            }
            case "THIRD_PARTY" -> {
                int statusCode = thirdParty.charge(identifier, "Product purchase", amount);
                if (statusCode == 200) {
                    System.out.println("Third-party payment successful\n");
                }
            }
        }
    }


}