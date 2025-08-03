package payment;

public enum PaymentType {
    CASH(new CashPaymentStrategy()),
    CARD(new CardPaymentStrategy()),
    PAYPAL(new PayPalPaymentStrategy());

    PaymentType(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    private final PaymentStrategy paymentStrategy;

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }
}
