package parking.gate;

import parking.ParkingLot;
import parking.Ticket;
import payment.PaymentType;

import java.time.LocalDateTime;

public class ExitGate extends Gate {

    public ExitGate(String id) {
        super(id);
    }

    @Override
    public GateType getType() {
        return GateType.EXIT;
    }

    public void exit(Ticket ticket, LocalDateTime exitTime, PaymentType paymentType) {
        ParkingLot.getInstance().exit(ticket.getId(), exitTime, paymentType);
    }
}
