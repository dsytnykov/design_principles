package product.modern;


import product.Chair;

public class ModernChair implements Chair {

    public ModernChair() {
        System.out.println("Modern Chair is created");
    }

    @Override
    public void hasLegs() {
        System.out.println("Modern Chair has legs");
    }

    @Override
    public void sitOn() {
        System.out.println("Modern Chair sits on");
    }
}
