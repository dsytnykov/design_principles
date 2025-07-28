package parking;

import org.junit.jupiter.api.Test;
import payment.PaymentStatusType;
import payment.PaymentType;
import vehicle.Vehicle;
import vehicle.VehicleFactory;
import vehicle.VehicleType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void shouldParkVehicle() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingFloor floor = new ParkingFloor("floorId");
        floor.addSpot(new ParkingSpot("id", VehicleType.CAR));
        parkingLot.addFloor(floor);
        Vehicle vehicle = VehicleFactory.create("ABC123", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.now();

        Ticket ticket = parkingLot.park(vehicle, entryTime);

        assertEquals(vehicle, ticket.getVehicle());
        assertEquals(entryTime, ticket.getEntryTime());
        assertEquals(vehicle.getVehicleType(), ticket.getVehicle().getVehicleType());
    }

    @Test
    void shouldExitVehicle() {
        ParkingLot parkingLot = ParkingLot.getInstance();
        ParkingFloor floor = new ParkingFloor("floorId");
        floor.addSpot(new ParkingSpot("id", VehicleType.CAR));
        parkingLot.addFloor(floor);
        Vehicle vehicle = VehicleFactory.create("ABC123", VehicleType.CAR);
        LocalDateTime entryTime = LocalDateTime.now();
        LocalDateTime exitTime = LocalDateTime.now().plusMinutes(60);
        Ticket ticket = parkingLot.park(vehicle, entryTime);
        PaymentType paymentType = PaymentType.CASH;

        parkingLot.exit(ticket.getId(), exitTime, paymentType);

        assertEquals(PaymentStatusType.COMPLETED, ticket.getPaymentStatus());
    }

}