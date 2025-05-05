public class Demo {
    public static void main(String[] args) {
        AnyShop shop = new AnyShop(new VictorianFurnitureFactory());
        shop.orderFurniture();

        AnyShop anotherShop = new AnyShop(new ModernFurnitureFactory());
        anotherShop.orderFurniture();
    }
}
