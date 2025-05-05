public class AnyShop {
    private final FurnitureFactory factory;

    public AnyShop(FurnitureFactory factory) {
        this.factory = factory;
    }

    public void orderFurniture() {
        factory.createChair();
        factory.createSofa();
        factory.createCoffeeTable();
    }
}
