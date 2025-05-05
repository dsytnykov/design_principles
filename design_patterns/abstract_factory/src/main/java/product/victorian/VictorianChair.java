package product.victorian;

import product.Chair;

public class VictorianChair implements Chair {

    public VictorianChair() {
        System.out.println("Victorian Chair is created");
    }

    @Override
    public void hasLegs() {
        System.out.println("Victorian Chair has small legs");
    }

    @Override
    public void sitOn() {
        System.out.println("Victorian Chair is sitting on");
    }
}
