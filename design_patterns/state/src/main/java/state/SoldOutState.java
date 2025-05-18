package state;

import machine.GumballMachine;

public class SoldOutState extends State {
    private final GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void refill() {
        gumballMachine.setState(gumballMachine.getNoCoinState());
    }

    @Override
    public String toString() {
        return "SoldOutState{}";
    }
}
