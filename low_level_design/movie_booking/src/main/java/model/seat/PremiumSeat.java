package model.seat;

import java.math.BigDecimal;

public class PremiumSeat extends Seat {
    public PremiumSeat(String id, BigDecimal price) {
        super(id, SeatType.PREMIUM, price);
    }
}
