package state;

import machine.GumballMachine;

public class NoCoinState extends State {
    private final GumballMachine gumballMachine;

    public NoCoinState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("You inserted a coin");
        gumballMachine.setState(gumballMachine.getHasCoinState());
    }

    @Override
    public String toString() {
        return "waiting for coin";
    }
}
