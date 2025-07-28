package payment;

import parking.Ticket;

public final class CardPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean pay(Ticket ticket, double amount) {
        System.out.println("Paid with card $" + amount + " for ticket " + ticket.getId());
        return true;
    }
}
