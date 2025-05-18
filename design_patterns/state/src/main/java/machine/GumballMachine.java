package machine;

import state.*;

public class GumballMachine {
    private final NoCoinState noCoinState;
    private final HasCoinState hasCoinState;
    private final SoldOutState soldOutState;
    private final SoldState soldState;
    private final WinnerState winnerState;

    private State state;
    private int count = 0;

    public GumballMachine(int count) {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);

        this.count = count;
        if(count > 0) {
            state = noCoinState;
        } else {
            state = soldOutState;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public void insertCoin() {
        state.insertCoin();
    }

    public void ejectCoin() {
        state.ejectCoin();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public int getCount() {
        return count;
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if(count != 0) {
            count = count - 1;
        }
    }
    
    public void refill(int count) {
        this.count += count;
        System.out.println("The gumball machine is refilled: " + count + " gumballs");
        state.refill();
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "noCoinState=" + noCoinState +
                ", hasCoinState=" + hasCoinState +
                ", soldOutState=" + soldOutState +
                ", soldState=" + soldState +
                ", state=" + state +
                ", count=" + count +
                '}';
    }
}
