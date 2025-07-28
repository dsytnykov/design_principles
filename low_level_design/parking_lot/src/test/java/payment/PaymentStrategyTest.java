package payment;

import org.junit.jupiter.api.Test;
import parking.Ticket;

import static org.junit.jupiter.api.Assertions.*;

class PaymentStrategyTest {

    @Test
    void shouldPayCash() {
        Ticket ticket = new Ticket.Builder()
                .id("id")
                .build();

        PaymentStrategy paymentStrategy = new CashPaymentStrategy();
        assertTrue(paymentStrategy.pay(ticket, 100.0));
    }

    @Test
    void shouldPayCard() {
        Ticket ticket = new Ticket.Builder()
                .id("id")
                .build();

        PaymentStrategy paymentStrategy = new CardPaymentStrategy();
        assertTrue(paymentStrategy.pay(ticket, 100.0));
    }

}