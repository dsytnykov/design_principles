package state;

import machine.GumballMachine;

import java.util.Random;

public class HasCoinState extends State {
    private final GumballMachine gumballMachine;
    private Random random = new Random();

    public HasCoinState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void ejectCoin() {
        System.out.println("Coin returned!");
        gumballMachine.setState(gumballMachine.getNoCoinState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        int winner = random.nextInt(10);
        if (winner == 0 && gumballMachine.getCount() > 1) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public String toString() {
        return "waiting for turn of crank";
    }
}
