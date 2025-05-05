import product.Chair;
import product.CoffeeTable;
import product.Sofa;
import product.victorian.VictorianChair;
import product.victorian.VictorianCoffeeTable;
import product.victorian.VictorianSofa;

public class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
}
