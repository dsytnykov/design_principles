package products.chicagostyle;

import products.Pizza;

public class ChicagoStyleCheesePizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Preparing ChicagoStyleCheesePizza");
    }

    @Override
    public void bake() {
        System.out.println("Bake ChicagoStyleCheesePizza");
    }

    @Override
    public void cut() {
        System.out.println("Cut ChicagoStyleCheesePizza");
    }

    @Override
    public void box() {
        System.out.println("Box ChicagoStyleCheesePizza");
    }
}
