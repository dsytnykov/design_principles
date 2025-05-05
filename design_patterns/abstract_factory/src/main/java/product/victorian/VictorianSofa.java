package product.victorian;

import product.Sofa;

public class VictorianSofa implements Sofa {

    public VictorianSofa() {
        System.out.println("Victorian sofa is created");
    }

    @Override
    public void hasLegs() {
        System.out.println("Victorian sofa has legs");
    }

    @Override
    public void canSleep() {
        System.out.println("Victorian sofa: you cannot sleep");
    }
}
