package payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTypeTest {

    @Test
    void shouldCreatePaymentStrategies() {
        assertInstanceOf(CashPaymentStrategy.class, PaymentType.CASH.getPaymentStrategy());
        assertInstanceOf(CardPaymentStrategy.class, PaymentType.CARD.getPaymentStrategy());
    }

}