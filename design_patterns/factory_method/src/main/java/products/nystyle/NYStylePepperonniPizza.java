package products.nystyle;

import products.Pizza;

public class NYStylePepperonniPizza implements Pizza {
    @Override
    public void prepare() {
        System.out.println("NYStylePepperonniPizza prepare");
    }

    @Override
    public void bake() {
        System.out.println("NYStylePepperonniPizza bake");
    }

    @Override
    public void cut() {
        System.out.println("NYStylePepperonniPizza cut");
    }

    @Override
    public void box() {
        System.out.println("NYStylePepperonniPizza box");
    }
}
