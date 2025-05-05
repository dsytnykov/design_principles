public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
}
