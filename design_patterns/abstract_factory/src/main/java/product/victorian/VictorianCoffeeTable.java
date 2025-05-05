package product.victorian;

import product.CoffeeTable;

public class VictorianCoffeeTable implements CoffeeTable {

    public VictorianCoffeeTable() {
        System.out.println("Victorian Coffee Table is created");
    }

    @Override
    public void hasLegs() {
        System.out.println("Victorian Coffee Table has legs");
    }

    @Override
    public void fromMaterial() {
        System.out.println("Victorian Coffee Table is from wood");
    }
}
