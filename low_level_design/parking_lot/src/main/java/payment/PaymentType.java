package payment;

public enum PaymentType {
    CASH(new CashPaymentStrategy()),
    CARD(new CardPaymentStrategy());

    private final PaymentStrategy paymentStrategy;

    PaymentType(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }
}
