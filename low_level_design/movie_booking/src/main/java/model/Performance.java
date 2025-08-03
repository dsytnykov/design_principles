package model;

import model.seat.Seat;

import java.time.LocalDateTime;
import java.util.List;

public class Performance {
    private final String id;
    private final Movie movie;
    private final Room room;
    private final Theater theater;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Performance(Builder builder) {
        this.id = builder.id;
        this.movie = builder.movie;
        this.room = builder.room;
        this.theater = builder.theater;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public Theater getTheater() {
        return theater;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Seat> getSeats() { return room.getSeats().values().stream().toList(); }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private Movie movie;
        private Room room;
        private Theater theater;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder movie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder room(Room room) {
            this.room = room;
            return this;
        }

        public Builder theater(Theater theater) {
            this.theater = theater;
            return this;
        }

        public Builder startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Performance build() {
            return new Performance(this);
        }
    }
}
