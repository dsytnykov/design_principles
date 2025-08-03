package model.booking;

import model.Performance;
import model.seat.Seat;
import payment.PaymentType;

import java.math.BigDecimal;
import java.util.List;

public class Booking {
    private final String id;
    private final String userId;
    private final String movieId;
    private final String roomId;
    private final List<Seat> seats;
    private final BigDecimal price;
    private PaymentType paymentType;
    private BookingStatus bookingStatus;
    private final Performance performance;

    public Booking(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.movieId = builder.movieId;
        this.roomId = builder.roomId;
        this.seats = builder.seats;
        this.price = builder.price;
        this.paymentType = builder.paymentType;
        this.bookingStatus = BookingStatus.PENDING;
        this.performance = builder.performance;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getRoomId() {
        return roomId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Performance getPerformance() {
        return performance;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String userId;
        private String movieId;
        private String roomId;
        private List<Seat> seats;
        private BigDecimal price;
        private PaymentType paymentType;
        private Performance performance;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder movieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder roomId(String roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder seats(List<Seat> seats) {
            this.seats = seats;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder performance(Performance performance) {
            this.performance = performance;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }

}
