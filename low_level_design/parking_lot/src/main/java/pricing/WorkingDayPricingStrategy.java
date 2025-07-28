package pricing;

import vehicle.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;

public final class WorkingDayPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        if (entryTime.isAfter(exitTime)) {
            throw new IllegalArgumentException("Exit time must be after entry time");
        }
        long duration = Duration.between(entryTime, exitTime).toMinutes();
        long totalHours = (long) Math.ceil(duration / 60.0);

        return totalHours * (switch (type) {
            case BIKE -> 1.0;
            case CAR -> 2.0;
            case MOBILE_HOME -> 5.0;
        });
    }
}
