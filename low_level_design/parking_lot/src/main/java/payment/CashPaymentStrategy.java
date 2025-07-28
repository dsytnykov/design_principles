package payment;

import parking.Ticket;

public final class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Ticket ticket, double amount) {
        System.out.println("Paid cash $" + amount + " for ticket " + ticket.getId());
        return true;
    }
}
