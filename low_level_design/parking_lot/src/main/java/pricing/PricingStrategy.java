package pricing;

import vehicle.VehicleType;

import java.time.LocalDateTime;

public sealed interface PricingStrategy permits WorkingDayPricingStrategy, WeekendPricingStrategy, HolidayPricingStrategy {
    double calculatePrice(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime);
}
