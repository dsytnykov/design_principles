import product.Chair;
import product.CoffeeTable;
import product.Sofa;

public interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    CoffeeTable createCoffeeTable();

}
