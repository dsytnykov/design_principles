package pricing;

public enum PricingType {
    WORKING_DAY(new WorkingDayPricingStrategy()),
    WEEKEND(new WeekendPricingStrategy()),
    HOLIDAY(new HolidayPricingStrategy());

    private final PricingStrategy pricingStrategy;

    PricingType(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
