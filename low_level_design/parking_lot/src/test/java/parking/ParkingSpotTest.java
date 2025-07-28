package parking;

import org.junit.jupiter.api.Test;
import vehicle.VehicleType;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {

    @Test
    void shouldCreateParkingSpot() {
        ParkingSpot parkingSpot = new ParkingSpot("id", VehicleType.CAR);
        assertEquals("id", parkingSpot.getId());
        assertEquals(VehicleType.CAR, parkingSpot.getAllowedVehicleType());
    }

    @Test
    void shouldTakeSpot() {
        ParkingSpot parkingSpot = new ParkingSpot("id", VehicleType.CAR);
        assertTrue(parkingSpot.take());
        assertFalse(parkingSpot.take());
    }

    @Test
    void shouldVacateSpot() {
        ParkingSpot parkingSpot = new ParkingSpot("id", VehicleType.CAR);

        assertTrue(parkingSpot.take());
        assertFalse(parkingSpot.isAvailable());
        parkingSpot.vacate();
        assertTrue(parkingSpot.isAvailable());
    }

}