package payment;

import parking.Ticket;

public class PaymentProcessor {
    private final PaymentStrategy paymentStrategy;

    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean pay(Ticket ticket, double amount) {
        if(paymentStrategy.pay(ticket, amount)) {
            ticket.setPaymentStatus(PaymentStatusType.COMPLETED);
            return true;
        }
        ticket.setPaymentStatus(PaymentStatusType.FAILED);
        System.out.println("Payment failed for the ticket " + ticket.getId());
        return false;
    }
}
