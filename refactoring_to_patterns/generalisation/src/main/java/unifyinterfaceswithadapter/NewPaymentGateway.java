package unifyinterfaceswithadapter;

class NewPaymentGateway {
    public String processTransaction(String cardToken, int amountInCents, String currency) {
        System.out.println("Processing payment via new gateway...");
        System.out.println("Token: " + cardToken + ", Amount: " + amountInCents + " " + currency);
        return "TXN-12345";
    }

    public boolean verifyTransaction(String txnId) {
        System.out.println("Transaction " + txnId + " verified");
        return true;
    }
}
