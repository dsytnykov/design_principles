package pricing;

import org.junit.jupiter.api.Test;
import vehicle.VehicleType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingStrategyTest {

    @Test
    void shouldCalculateWorkingDayPrice() {
        PricingStrategy pricingStrategy = new WorkingDayPricingStrategy();

        double actualCarPrice = pricingStrategy.calculatePrice(VehicleType.CAR, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
        double actualBikePrice = pricingStrategy.calculatePrice(VehicleType.BIKE, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
        double actualMobileHomePrice = pricingStrategy.calculatePrice(VehicleType.MOBILE_HOME, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));

        assertEquals(2.0, actualCarPrice);
        assertEquals(1.0, actualBikePrice);
        assertEquals(5.0, actualMobileHomePrice);
    }

    @Test
    void shouldCalculateWeekendPrice() {
        PricingStrategy pricingStrategy = new WeekendPricingStrategy();

        double actualCarPrice = pricingStrategy.calculatePrice(VehicleType.CAR, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
        double actualBikePrice = pricingStrategy.calculatePrice(VehicleType.BIKE, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
        double actualMobileHomePrice = pricingStrategy.calculatePrice(VehicleType.MOBILE_HOME, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));

        assertEquals(0, actualCarPrice);
        assertEquals(0, actualBikePrice);
        assertEquals(0, actualMobileHomePrice);
    }

    @Test
    void shouldCalculateHolidayPrice() {
        PricingStrategy pricingStrategy = new HolidayPricingStrategy();

        double actualCarPrice = pricingStrategy.calculatePrice(VehicleType.CAR, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
        double actualBikePrice = pricingStrategy.calculatePrice(VehicleType.BIKE, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));
        double actualMobileHomePrice = pricingStrategy.calculatePrice(VehicleType.MOBILE_HOME, LocalDateTime.now(), LocalDateTime.now().plusMinutes(60));

        assertEquals(1.0, actualCarPrice);
        assertEquals(1.0, actualBikePrice);
        assertEquals(1.0, actualMobileHomePrice);
    }

}