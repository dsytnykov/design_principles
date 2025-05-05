import behavior.fly.NoFlyableBehavior;
import behavior.quack.NoQuack;

public class ArtificialDuck extends Duck {

    public ArtificialDuck() {
        flyBehavior = new NoFlyableBehavior();
        quackBehavior = new NoQuack();
    }

    @Override
    public void display() {
        System.out.println("Artificial duck");
    }
}
