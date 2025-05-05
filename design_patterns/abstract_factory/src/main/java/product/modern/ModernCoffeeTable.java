package product.modern;

import product.CoffeeTable;

public class ModernCoffeeTable implements CoffeeTable {

    public ModernCoffeeTable() {
        System.out.println("Modern coffee table is created");
    }

    @Override
    public void hasLegs() {
        System.out.println("Modern coffee table has no legs");
    }

    @Override
    public void fromMaterial() {
        System.out.println("Modern coffee table is made of glass");
    }
}
