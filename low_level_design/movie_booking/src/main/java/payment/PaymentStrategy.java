package payment;

import model.booking.Booking;

public interface PaymentStrategy {
    boolean pay(Booking booking);
}
