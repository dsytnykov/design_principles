import product.Chair;
import product.CoffeeTable;
import product.Sofa;
import product.modern.ModernChair;
import product.modern.ModernCoffeeTable;
import product.modern.ModernSofa;

public class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}
