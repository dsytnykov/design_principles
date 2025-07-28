package parking;

import vehicle.VehicleType;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private final String id;
    private final VehicleType vehicleType;
    private final AtomicBoolean available = new AtomicBoolean(true);

    public ParkingSpot(String id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }

    public boolean take() {
        return available.compareAndSet(true, false);
    }

    public void vacate(){
        available.set(true);
    }

    public boolean isAvailable() {
        return available.get();
    }

    public VehicleType getAllowedVehicleType() {return vehicleType;}

    public String getId() {
        return id;
    }
}
