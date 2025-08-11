package service;

import model.Performance;
import model.booking.Booking;
import model.booking.BookingStatus;
import model.seat.Seat;
import payment.PaymentStrategy;
import payment.PaymentType;
import repository.BookingRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class BookingService {
    public static final long LOCK_TTL = 5000L;//5s to test ttl expiration

    private final BookingRepository bookingRepository;
    private final LockSeatService lockSeatService;

    public BookingService(BookingRepository bookingRepository, LockSeatService lockSeatService) {
        this.bookingRepository = bookingRepository;
        this.lockSeatService = lockSeatService;
    }

    public Booking book(String userId, Performance performance, List<Seat> seats) {
        for( Seat seat : seats) {
            String key = String.format("%s:%s", performance.getId(), seat.getId());
            if(!lockSeatService.tryLock(key, userId, LOCK_TTL)) {
                throw new IllegalStateException(String.format("Seat %s is already locked", seat.getId()));
            }
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        for(Seat seat : seats) {
            if(seats.contains(seat)) {
                totalPrice = totalPrice.add(seat.getPrice());
            }
        }

        Booking booking = Booking.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .performance(performance)
                .seats(seats)
                .price(totalPrice)
                .paymentType(null)
                .build();
        bookingRepository.save(booking);
        System.out.println("Booking created: " + booking.getId());
        return booking;
    }

    public void payBooking(Booking booking, PaymentType paymentType) {
        if(booking.getBookingStatus() != BookingStatus.PENDING) {
            throw new IllegalStateException("Booking is not in a valid status");
        }

        for(Seat seat : booking.getSeats()) {
            String key = String.format("%s:%s", booking.getPerformance().getId(), seat.getId());
            if(lockSeatService.isLockExpired(key) || !lockSeatService.isLockedBy(key, booking.getUserId())) {
                throw new IllegalStateException(String.format("Seat %s is not locked by user %s", seat.getId(), booking.getUserId()));
            }
        }

        booking.setPaymentType(paymentType);
        PaymentStrategy paymentStrategy = paymentType.getPaymentStrategy();
        paymentStrategy.pay(booking);

        for(Seat seat: booking.getSeats()) {
            String key = String.format("%s:%s", booking.getPerformance().getId(), seat.getId());
            lockSeatService.unlock(key);
        }
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        System.out.println("Booking paid: " + booking.getId());
    }
}
