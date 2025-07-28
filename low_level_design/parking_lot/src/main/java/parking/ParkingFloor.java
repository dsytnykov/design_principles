package parking;

import vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {

    private final String id;
    private final List<ParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingFloor(String id) {
        this.id = id;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void addSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    public Optional<ParkingSpot> getParkingSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getAllowedVehicleType() == vehicleType && spot.take()) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }

    public String getId() {
        return id;
    }
}
