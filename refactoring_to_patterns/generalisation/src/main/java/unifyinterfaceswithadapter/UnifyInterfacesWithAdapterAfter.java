package unifyinterfaceswithadapter;

// AFTER REFACTORING
// Solution: Unified interface with adapters for each payment system

public class UnifyInterfacesWithAdapterAfter {
    public static void main(String[] args) {
        PaymentProcessorAfter processor = new PaymentProcessorAfter();

        // Create adapters for each payment system
        PaymentService oldPayment = new OldPaymentAdapter(new OldPaymentSystem());
        PaymentService newPayment = new NewPaymentAdapter(new NewPaymentGateway());
        PaymentService thirdPartyPayment = new ThirdPartyPaymentAdapter(new ThirdPartyPaymentService());

        // Process payments uniformly through the same interface
        processor.processPayment(oldPayment, "ACC-789", 99.99);
        processor.processPayment(newPayment, "TOK-456", 149.50);
        processor.processPayment(thirdPartyPayment, "CUST-123", 199.99);

        // Can easily switch between payment methods
        demonstratePolymorphism(oldPayment);
        demonstratePolymorphism(newPayment);
    }

    // Works with any payment service through unified interface
    private static void demonstratePolymorphism(PaymentService service) {
        System.out.println("Processing another payment...");
        service.processPayment("ID-999", 50.00);
    }
}


// Unified interface that all adapters will implement
interface PaymentService {
    boolean processPayment(String identifier, double amount);
}

// Adapter for old payment system
class OldPaymentAdapter implements PaymentService {
    private final OldPaymentSystem oldSystem;

    public OldPaymentAdapter(OldPaymentSystem oldSystem) {
        this.oldSystem = oldSystem;
    }

    @Override
    public boolean processPayment(String identifier, double amount) {
        return oldSystem.makePayment(identifier, amount);
    }
}

// Adapter for new payment gateway
class NewPaymentAdapter implements PaymentService {
    private final NewPaymentGateway gateway;

    public NewPaymentAdapter(NewPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public boolean processPayment(String identifier, double amount) {
        int amountInCents = (int) (amount * 100);
        String txnId = gateway.processTransaction(identifier, amountInCents, "USD");
        return gateway.verifyTransaction(txnId);
    }
}

// Adapter for third-party service
class ThirdPartyPaymentAdapter implements PaymentService {
    private final ThirdPartyPaymentService service;

    public ThirdPartyPaymentAdapter(ThirdPartyPaymentService service) {
        this.service = service;
    }

    @Override
    public boolean processPayment(String identifier, double amount) {
        int statusCode = service.charge(identifier, "Product purchase", amount);
        return statusCode == 200;
    }
}

// Client code is now much simpler and uniform
class PaymentProcessorAfter {
    public void processPayment(PaymentService paymentService, String identifier, double amount) {
        boolean success = paymentService.processPayment(identifier, amount);
        if (success) {
            System.out.println("Payment successful\n");
        } else {
            System.out.println("Payment failed\n");
        }
    }

}