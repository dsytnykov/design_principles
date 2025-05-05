public class Demo {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        CondimentDecorator decorator = new Milk(beverage);
        decorator = new Mocha(decorator);
        System.out.println(decorator.getDescription() + " $" + decorator.cost());
    }
}
