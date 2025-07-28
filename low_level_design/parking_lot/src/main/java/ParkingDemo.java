import parking.ParkingFloor;
import parking.ParkingLot;
import parking.ParkingSpot;
import parking.Ticket;
import parking.gate.EntryGate;
import parking.gate.ExitGate;
import payment.PaymentType;
import pricing.PricingType;
import vehicle.Vehicle;
import vehicle.VehicleFactory;
import vehicle.VehicleType;

import java.time.LocalDateTime;

public class ParkingDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        EntryGate entryGate = new EntryGate("Entry Gate 1");
        ExitGate exitGate = new ExitGate("Exit Gate 1");

        parkingLot.setPricingStrategy(PricingType.WORKING_DAY.getPricingStrategy());

        ParkingFloor floor1 = new ParkingFloor("Floor 1");
        ParkingFloor floor2 = new ParkingFloor("Floor 2");
        floor1.addSpot(new ParkingSpot("Spot 1", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("Spot 2", VehicleType.CAR));
        floor2.addSpot(new ParkingSpot("Spot 3", VehicleType.BIKE));
        floor2.addSpot(new ParkingSpot("Spot 4", VehicleType.CAR));
        floor2.addSpot(new ParkingSpot("Spot 5", VehicleType.MOBILE_HOME));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        System.out.println("========================================");
        Vehicle car = VehicleFactory.create("ABC123", VehicleType.CAR);

        LocalDateTime entryTime = LocalDateTime.parse("2025-07-17T13:45:00");

        Ticket ticket = entryGate.park(car, entryTime);

        System.out.println("========================================");

        LocalDateTime exitTime = LocalDateTime.parse("2025-07-17T16:00:00");

        exitGate.exit(ticket, exitTime, PaymentType.CASH);

        System.out.println("========================================");
    }
}
