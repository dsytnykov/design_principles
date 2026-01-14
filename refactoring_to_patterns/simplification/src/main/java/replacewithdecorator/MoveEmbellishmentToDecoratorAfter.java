package replacewithdecorator;

// AFTER REFACTORING
// Solution: Use Decorator pattern to add features dynamically

public class MoveEmbellishmentToDecoratorAfter {
    public static void main(String[] args) {
        Pizza pizza1 = new MargheritaPizza();
        pizza1 = new ExtraCheeseDecorator(pizza1);
        System.out.println(pizza1.getDescription() + " costs $" + pizza1.cost());

        Pizza pizza2 = new PepperoniPizza();
        pizza2 = new OlivesDecorator(pizza2);
        System.out.println(pizza2.getDescription() + " costs $" + pizza2.cost());

        Pizza pizza3 = new MargheritaPizza();
        pizza3 = new ExtraCheeseDecorator(pizza3);
        pizza3 = new MushroomsDecorator(pizza3);
        System.out.println(pizza3.getDescription() + " costs $" + pizza3.cost());

        Pizza pizza4 = new PepperoniPizza();
        pizza4 = new ExtraCheeseDecorator(pizza4);
        pizza4 = new OlivesDecorator(pizza4);
        pizza4 = new MushroomsDecorator(pizza4);
        pizza4 = new PeppersDecorator(pizza4);
        pizza4 = new OnionsDecorator(pizza4);
        System.out.println(pizza4.getDescription() + " costs $" + pizza4.cost());

        Pizza pizza5 = new VeggiePizza();
        pizza5 = new ExtraCheeseDecorator(pizza5);
        pizza5 = new ExtraCheeseDecorator(pizza5);
        System.out.println(pizza5.getDescription() + " costs $" + pizza5.cost());

        System.out.println("\nSolution: Any combination possible with just a few classes!");
    }
}

class VeggiePizza extends Pizza {
    public VeggiePizza() {
        description = "Veggie Pizza";
    }

    @Override
    public double cost() {
        return 9.49;
    }
}

// Abstract Decorator
abstract class ToppingDecorator extends Pizza {
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public abstract String getDescription();
}

// Concrete Decorators (toppings)
class ExtraCheeseDecorator extends ToppingDecorator {
    public ExtraCheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.50;
    }
}

class MushroomsDecorator extends ToppingDecorator {
    public MushroomsDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushrooms";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.25;
    }
}

class OlivesDecorator extends ToppingDecorator {
    public OlivesDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.00;
    }
}

class PeppersDecorator extends ToppingDecorator {
    public PeppersDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Peppers";
    }

    @Override
    public double cost() {
        return pizza.cost() + 0.75;
    }
}

class OnionsDecorator extends ToppingDecorator {
    public OnionsDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Onions";
    }

    @Override
    public double cost() {
        return pizza.cost() + 0.50;
    }
}

