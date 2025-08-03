package payment;

import model.booking.Booking;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        System.out.println("Paid successfully with cash  " + booking.getUserId() + " for booking " + booking.getId() + " price " + booking.getPrice());
        return true;
    }
}
