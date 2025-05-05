import products.Pizza;
import products.chicagostyle.ChicagoStyleCheesePizza;
import products.chicagostyle.ChicagoStylePepperonniPizza;

public class ChicagoStylePizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        }
        return new ChicagoStylePepperonniPizza();
    }
}
