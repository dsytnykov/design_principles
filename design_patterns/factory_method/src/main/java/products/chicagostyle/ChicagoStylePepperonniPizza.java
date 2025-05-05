package products.chicagostyle;

import products.Pizza;

public class ChicagoStylePepperonniPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("Prepare ChicagoStylePepperonniPizza");
    }

    @Override
    public void bake() {
        System.out.println("Bake ChicagoStylePepperonniPizza");
    }

    @Override
    public void cut() {
        System.out.println("Cut ChicagoStylePepperonniPizza");
    }

    @Override
    public void box() {
        System.out.println("Box ChicagoStylePepperonniPizza");
    }
}
