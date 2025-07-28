package payment;

import parking.Ticket;

public sealed interface PaymentStrategy permits CardPaymentStrategy, CashPaymentStrategy {
    boolean pay(Ticket ticket, double amount);
}
