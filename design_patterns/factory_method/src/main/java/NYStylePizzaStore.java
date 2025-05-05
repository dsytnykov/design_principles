import products.Pizza;
import products.nystyle.NYStyleCheesePizza;
import products.nystyle.NYStylePepperonniPizza;

public class NYStylePizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")) {
            return new NYStyleCheesePizza();
        }
        return new NYStylePepperonniPizza();
    }
}
