package pricing;

import vehicle.VehicleType;

import java.time.LocalDateTime;

public final class WeekendPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        return 0;
    }
}
