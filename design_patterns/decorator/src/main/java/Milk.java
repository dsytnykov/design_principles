public class Milk extends CondimentDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return 0.1 + beverage.cost();
    }

    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }
}
