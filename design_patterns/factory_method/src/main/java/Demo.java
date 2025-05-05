public class Demo {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYStylePizzaStore();
        nyStore.orderPizza("cheese");
        System.out.println();

        PizzaStore chicagoStore = new ChicagoStylePizzaStore();
        chicagoStore.orderPizza("pepperonni");
    }
}
