package unifyinterfaceswithadapter;

// Legacy payment processor
class OldPaymentSystem {
    public boolean makePayment(String accountNumber, double amount) {
        System.out.println("Processing payment via old system...");
        System.out.println("Account: " + accountNumber + ", Amount: $" + amount);
        return true;
    }

    public String getStatus(String transactionId) {
        return "COMPLETED";
    }
}
