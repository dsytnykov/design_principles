package parking.gate;

import parking.ParkingLot;
import parking.Ticket;
import vehicle.Vehicle;

import java.time.LocalDateTime;

public class EntryGate extends Gate {

    public EntryGate(String id) {
        super(id);
    }

    @Override
    public GateType getType() {
        return GateType.ENTRY;
    }

    public Ticket park(Vehicle vehicle, LocalDateTime entryTime) {
        return ParkingLot.getInstance().park(vehicle, entryTime);
    }
}
