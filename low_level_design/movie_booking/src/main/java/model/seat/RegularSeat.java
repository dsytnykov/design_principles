package model.seat;

import java.math.BigDecimal;

public class RegularSeat extends Seat {

    public RegularSeat(String id, BigDecimal price) {
        super(id, SeatType.REGULAR, price);
    }

}
