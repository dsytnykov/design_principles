package payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTypeTest {

    @Test
    void shouldReturnCashPaymentStrategy() {
        PaymentType paymentType = PaymentType.CASH;
        PaymentStrategy paymentStrategy = paymentType.getPaymentStrategy();

        assertInstanceOf(CashPaymentStrategy.class, paymentStrategy);
    }

    @Test
    void shouldReturnCardPaymentStrategy() {
        PaymentType paymentType = PaymentType.CARD;
        PaymentStrategy paymentStrategy = paymentType.getPaymentStrategy();

        assertInstanceOf(CardPaymentStrategy.class, paymentStrategy);
    }
    @Test
    void shouldReturnPayPalPaymentStrategy() {
        PaymentType paymentType = PaymentType.PAYPAL;
        PaymentStrategy paymentStrategy = paymentType.getPaymentStrategy();

        assertInstanceOf(PayPalPaymentStrategy.class, paymentStrategy);
    }

}