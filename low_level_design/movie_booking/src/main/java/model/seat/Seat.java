package model.seat;

import java.math.BigDecimal;

public abstract class Seat {
    private String id;
    private SeatType type;
    private BigDecimal price;

    public Seat(String id, SeatType type, BigDecimal price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public SeatType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
