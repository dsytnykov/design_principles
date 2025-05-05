import behavior.fly.FlyWithWings;
import behavior.quack.Quack;

public class EuropeDuck extends Duck {

    public EuropeDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("EuropeDuck");
    }
}
