package state;

public abstract class State {

    public void insertCoin() {
        System.out.println("You can't insert another coin!");
    }

    public void ejectCoin() {
        System.out.println("You haven't inserted a coin");
    }

    public void turnCrank() {
        System.out.println("You turned, but there's no coin");
    }

    public void dispense() {
        System.out.println("You need to pay first");
    }

    public void refill() {
        System.out.println("Refill gumballs");
    }
}
