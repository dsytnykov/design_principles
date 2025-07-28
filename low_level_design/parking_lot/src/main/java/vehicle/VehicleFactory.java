package vehicle;

public class VehicleFactory {

    public static Vehicle create(String licensePlate, VehicleType vehicleType) {
        return switch(vehicleType) {
            case BIKE -> new Bike(licensePlate);
            case CAR -> new Car(licensePlate);
            case MOBILE_HOME -> new MobileHome(licensePlate);
        };
    }
}
