package parking;

import payment.PaymentProcessor;
import payment.PaymentType;
import pricing.PricingStrategy;
import pricing.PricingType;
import vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();

    private final List<ParkingFloor> parkingFloors = new ArrayList<>();
    private final List<Ticket> activeTickets = new ArrayList<>();
    private PricingStrategy pricingStrategy;

    private ParkingLot() {
        this.pricingStrategy = PricingType.WORKING_DAY.getPricingStrategy();
    }

    public static ParkingLot getInstance() {
        return INSTANCE;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void addFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }

    public Ticket park(Vehicle vehicle, LocalDateTime entryTime) {
        for(ParkingFloor floor : parkingFloors) {
            Optional<ParkingSpot> spot = floor.getParkingSpot(vehicle.getVehicleType());
            if(spot.isPresent()) {
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket.Builder()
                        .id(ticketId)
                        .entryTime(entryTime)
                        .vehicle(vehicle)
                        .floorId(floor.getId())
                        .spotId(spot.get().getId())
                        .build();
                activeTickets.add(ticket);
                System.out.println("Vehicle parked with ticket nummer " + ticketId + " at " + entryTime);
                return ticket;
            }
        }
        System.out.println("No available spot for this vehicle " + vehicle.getVehicleType());
        return new Ticket.Builder().build();
    }

    public void exit(String ticketId, LocalDateTime exitTime, PaymentType paymentType) {
        Optional<Ticket> ticketDetails = activeTickets.stream().filter(ticket -> ticket.getId().equals(ticketId)).findFirst();
        if(ticketDetails.isPresent()) {
            Ticket ticket = ticketDetails.get();
            double price = pricingStrategy.calculatePrice(ticket.getVehicle().getVehicleType(), ticket.getEntryTime(), exitTime);
            PaymentProcessor processor = new PaymentProcessor(paymentType.getPaymentStrategy());
            boolean paid = processor.pay(ticket, price);
            if (!paid) {
                System.out.println("Ticket " + ticketId + "payment failed, please try again");
                return;
            }
            Optional<ParkingFloor> floor = parkingFloors.stream()
                    .filter(f -> f.getId().equals(ticket.getFloorId()))
                    .findFirst();
            if (floor.isPresent()) {
                Optional<ParkingSpot> spot = floor.get().getParkingSpots().stream()
                        .filter(s -> s.getId().equals(ticket.getSpotId()))
                        .findFirst();
                spot.ifPresent(ParkingSpot::vacate);
                activeTickets.remove(ticket);

                System.out.println("Vehicle left with ticket number " + ticketId +  " at " + exitTime + " and paid " + price);
            }
        } else {
            System.out.println("Ticket " + ticketId + " not found");
        }
    }
}
