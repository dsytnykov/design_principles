package payment;

import model.booking.Booking;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean pay(Booking booking) {
        System.out.println("Paid successfully by card " + booking.getUserId() + " for booking " + booking.getId() + " price " + booking.getPrice());
        return true;
    }
}
