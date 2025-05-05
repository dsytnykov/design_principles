import behavior.quack.Quack;

public class Demo {
    public static void main(String[] args) {
        Duck duck = new ArtificialDuck();
        duck.performFly();
        duck.performQuack();

        duck.setQuackBehavior(new Quack());
        duck.performQuack();
    }
}
