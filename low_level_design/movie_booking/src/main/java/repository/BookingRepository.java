package repository;

import model.booking.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    public void save(Booking booking) {
        bookings.putIfAbsent(booking.getId(), booking);
    }

    public Booking findById(String id) {
        return bookings.get(id);
    }
}
