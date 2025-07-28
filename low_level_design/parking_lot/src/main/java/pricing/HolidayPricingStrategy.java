package pricing;

import vehicle.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;

public final class HolidayPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        long minutes = Duration.between(entryTime, exitTime).toMinutes();
        long totalHours = (long) Math.ceil(minutes / 60.0);
        return totalHours * 1.0;
    }
}
