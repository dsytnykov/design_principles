package behavior.fly;

public class NoFlyableBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
