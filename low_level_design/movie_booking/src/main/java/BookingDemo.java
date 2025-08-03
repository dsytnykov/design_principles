import model.Movie;
import model.Performance;
import model.Room;
import model.Theater;
import model.booking.Booking;
import model.seat.PremiumSeat;
import model.seat.RegularSeat;
import model.seat.Seat;
import payment.PaymentType;
import repository.BookingRepository;
import repository.MovieRepository;
import repository.PerformanceRepository;
import repository.TheaterRepository;
import service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static service.BookingService.LOCK_TTL;

public class BookingDemo {
    public static void main(String[] args) throws InterruptedException {
        BookingRepository bookingRepository = new BookingRepository();
        PerformanceRepository performanceRepository = new PerformanceRepository();
        TheaterRepository theaterRepository = new TheaterRepository();
        MovieRepository movieRepository = new MovieRepository();

        LockSeatService lockService = new LockSeatService();
        BookingService bookingService = new BookingService(bookingRepository, lockService);
        PerformanceService performanceService = new PerformanceService(performanceRepository);
        TheaterService theaterService = new TheaterService(theaterRepository);
        MovieService movieService = new MovieService(movieRepository);

        List<Seat> seats = List.of(
                new RegularSeat("r1s1", new BigDecimal("10")),
                new RegularSeat("r1s2", new BigDecimal("10")),
                new RegularSeat("r1s3", new BigDecimal("10")),
                new PremiumSeat("r1s4", new BigDecimal("20")),
                new PremiumSeat("r1s5", new BigDecimal("20")));

        Room room = new Room("r1");
        seats.forEach(room::addSeat);

        Room room2 = new Room("r2");
        seats.forEach(room2::addSeat);

        Theater theater = new Theater("tr1", "Theater 1", Map.of(room.getId(), room, room2.getId(), room2));
        theaterService.addTheater(theater);

        Movie movie = new Movie("m1", "Movie 1", "Action", 120);

        Performance performance = Performance.builder()
                .id("p1")
                .movie(movie)
                .room(room)
                .theater(theater)
                .startTime(LocalDateTime.of(2025, 1, 1, 10, 0))
                .endTime(LocalDateTime.of(2025, 1, 1, 12, 0))
                .build();

        System.out.println("--------------Start demo--------------");
        System.out.println("User books seats and pays for the booking");
        Booking booking = bookingService.book("user1",performance, seats.subList(0, 2));
        bookingService.payBooking(booking, PaymentType.CARD);

        System.out.println();
        System.out.println("User books seats and wait more that ttl before paying for the booking");
        booking = bookingService.book("user2",performance, seats.subList(2, 4));
        Thread.sleep(LOCK_TTL + 1000);
        try {
            bookingService.payBooking(booking, PaymentType.CARD);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("Two users try to book simultaneously and only one of them succeeds");
        ExecutorService executors = Executors.newFixedThreadPool(2);
        executors.submit(() -> {
            try {
                Booking b = bookingService.book("user3",performance, seats.subList(0, 2));
                Thread.sleep(1000);
                bookingService.payBooking(b, PaymentType.CARD);
            } catch (Exception e) {
                System.out.println("Error for user 3: " + e.getMessage());
            }
        });
        executors.submit(() -> {
            try {
                Booking b = bookingService.book("user4",performance, seats.subList(1,3));
                Thread.sleep(1000);
                bookingService.payBooking(b, PaymentType.PAYPAL);
            } catch (Exception e) {
                System.out.println("Error for user4: " + e.getMessage());
            }
        });
        executors.shutdown();

        lockService.close();
    }
}
