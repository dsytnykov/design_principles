package parking;

import org.junit.jupiter.api.Test;
import vehicle.VehicleType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParkingFloorTest {

    @Test
    void shouldAddSpot() {
        ParkingFloor parkingFloor = new ParkingFloor("floorId");
        ParkingSpot parkingSpot = new ParkingSpot("id", VehicleType.CAR);
        parkingFloor.addSpot(parkingSpot);
        assertEquals(1, parkingFloor.getParkingSpots().size());
    }

    @Test
    void shouldGetParkingSpot() {
        ParkingFloor parkingFloor = new ParkingFloor("floorId");
        ParkingSpot parkingSpot = new ParkingSpot("id", VehicleType.CAR);
        parkingFloor.addSpot(parkingSpot);
        assertEquals(Optional.of(parkingSpot), parkingFloor.getParkingSpot(VehicleType.CAR));
    }

    @Test
    void shouldNotGetParkingSpot() {
        ParkingFloor parkingFloor = new ParkingFloor("floorId");
        ParkingSpot parkingSpot = new ParkingSpot("id", VehicleType.BIKE);
        parkingFloor.addSpot(parkingSpot);
        assertEquals(Optional.empty(), parkingFloor.getParkingSpot(VehicleType.CAR));
    }

}