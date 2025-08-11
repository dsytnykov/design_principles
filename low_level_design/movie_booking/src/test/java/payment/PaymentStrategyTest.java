package payment;

import model.booking.Booking;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PaymentStrategyTest {
    @Test
    void shouldPayWithPayPal() {
        PaymentStrategy paymentStrategy = new PayPalPaymentStrategy();
        Booking booking = Booking.builder()
                .paymentType(PaymentType.PAYPAL)
                .userId("anyUserId")
                .price(new BigDecimal("10.00"))
                .build();
        assertTrue(paymentStrategy.pay(booking));
    }

    @Test
    void shouldPayWithCash() {
        PaymentStrategy paymentStrategy = new PayPalPaymentStrategy();
        Booking booking = Booking.builder()
                .paymentType(PaymentType.CASH)
                .userId("anyUserId")
                .price(new BigDecimal("10.00"))
                .build();
        assertTrue(paymentStrategy.pay(booking));
    }

    @Test
    void shouldPayWithCard() {
        PaymentStrategy paymentStrategy = new PayPalPaymentStrategy();
        Booking booking = Booking.builder()
                .paymentType(PaymentType.CARD)
                .userId("anyUserId")
                .price(new BigDecimal("10.00"))
                .build();
        assertTrue(paymentStrategy.pay(booking));
    }


}