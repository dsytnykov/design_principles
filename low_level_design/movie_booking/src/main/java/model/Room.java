package model;

import model.seat.Seat;

import java.util.HashMap;
import java.util.Map;

public class Room {
    private final  String id;
    private final  Map<String, Seat> seats = new HashMap<>();

    public Room(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addSeat(Seat seat) {
        seats.putIfAbsent(seat.getId(), seat);
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

}
