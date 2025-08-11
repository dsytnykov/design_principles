package service;

import model.Performance;
import model.booking.Booking;
import model.booking.BookingStatus;
import model.seat.RegularSeat;
import model.seat.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import payment.PaymentType;
import repository.BookingRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    private BookingService bookingService;
    private Performance performance;
    List<Seat> seats;

    @Mock
    private LockSeatService lockSeatService;

    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        bookingService = new BookingService(bookingRepository, lockSeatService);

        performance = Performance.builder()
                .id("perfId")
                .build();
        seats = List.of(new RegularSeat("seatId", new BigDecimal("10")));

    }

    @Test
    void shouldBookSuccessfully() {
        when(lockSeatService.tryLock(anyString(), anyString(), anyLong())).thenReturn(true);

        Booking actual = bookingService.book("1", performance, seats);

        assertEquals("1", actual.getUserId());
        assertEquals(performance, actual.getPerformance());
        assertEquals(seats, actual.getSeats());
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @Test
    void shouldFailBook() {
        when(lockSeatService.tryLock(anyString(), anyString(), anyLong())).thenReturn(false);
        Performance performance = Performance.builder()
                .id("perfId")
                .build();
        List<Seat> seats = List.of(new RegularSeat("seatId", new BigDecimal("10")));

        assertThrows(IllegalStateException.class, () -> bookingService.book("1", performance, seats));
    }

    @Test
    void shouldPayBookingSuccessfully() {
        when(lockSeatService.isLockExpired(anyString())).thenReturn(false);
        when(lockSeatService.isLockedBy(anyString(), anyString())).thenReturn(true);
        Booking booking = Booking.builder()
                .id("bookingId")
                .performance(performance)
                .seats(seats)
                .price(new BigDecimal("10.00"))
                .userId("userId")
                .build();

        bookingService.payBooking(booking, PaymentType.CASH);

        verify(lockSeatService, times(1)).unlock(anyString());
        assertEquals(BookingStatus.CONFIRMED, booking.getBookingStatus());
    }

    @Test
    void shouldFailPayBookingBecauseBookingStatus() {
        Booking booking = Booking.builder()
                .id("bookingId")
                .build();
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        assertThrows(IllegalStateException.class, () -> bookingService.payBooking(booking, PaymentType.CASH));
    }

    @Test
    void shouldFailPayBookingBecauseLockingIsExpired() {
        when(lockSeatService.isLockExpired(anyString())).thenReturn(true);
        Booking booking = Booking.builder()
                .id("bookingId")
                .performance(performance)
                .seats(seats)
                .price(new BigDecimal("10.00"))
                .build();

        assertThrows(IllegalStateException.class, () -> bookingService.payBooking(booking, PaymentType.CASH));
    }

    @Test
    void shouldFailPayBookingBecauseNotLockedByUser() {
        when(lockSeatService.isLockExpired(anyString())).thenReturn(false);
        when(lockSeatService.isLockedBy(anyString(), anyString())).thenReturn(false);
        Booking booking = Booking.builder()
                .id("bookingId")
                .performance(performance)
                .seats(seats)
                .price(new BigDecimal("10.00"))
                .userId("userId")
                .build();

        assertThrows(IllegalStateException.class, () -> bookingService.payBooking(booking, PaymentType.CASH));
    }
}