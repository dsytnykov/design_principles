package product.modern;


import product.Sofa;

public class ModernSofa implements Sofa {

    public ModernSofa() {
        System.out.println("Modern sofa is created");
    }

    @Override
    public void hasLegs() {
        System.out.println("Modern sofa has legs");
    }

    @Override
    public void canSleep() {
        System.out.println("Modern sofa: you can sleep");
    }
}
