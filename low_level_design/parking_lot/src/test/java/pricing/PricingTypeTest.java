package pricing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class PricingTypeTest {

    @Test
    void shouldCreatePricingStrategies() {
        assertInstanceOf(WorkingDayPricingStrategy.class, PricingType.WORKING_DAY.getPricingStrategy());
        assertInstanceOf(WeekendPricingStrategy.class, PricingType.WEEKEND.getPricingStrategy());
        assertInstanceOf(HolidayPricingStrategy.class, PricingType.HOLIDAY.getPricingStrategy());
    }
}