package replacewithdecorator;

// BEFORE REFACTORING
// Problem: Subclass explosion or complex conditional logic for adding features
// This approach leads to class explosion!
// With 4 base pizzas and 5 toppings, you'd need dozens of classes
// for every possible combination

public class MoveEmbellishmentToDecoratorBefore {
    public static void main(String[] args) {
        Pizza pizza1 = new MargheritaWithCheese();
        System.out.println(pizza1.getDescription() + " costs $" + pizza1.cost());

        Pizza pizza2 = new PepperoniWithOlives();
        System.out.println(pizza2.getDescription() + " costs $" + pizza2.cost());

        Pizza pizza3 = new MargheritaWithCheeseAndMushrooms();
        System.out.println(pizza3.getDescription() + " costs $" + pizza3.cost());

        System.out.println("\nProblem: Need a new class for every topping combination!");
    }
}

// Problem: Need subclasses for every combination!
class MargheritaWithCheese extends Pizza {
    public MargheritaWithCheese() {
        description = "Margherita Pizza with Extra Cheese";
    }

    @Override
    public double cost() {
        return 8.99 + 1.50;
    }
}

class MargheritaWithCheeseAndMushrooms extends Pizza {
    public MargheritaWithCheeseAndMushrooms() {
        description = "Margherita Pizza with Extra Cheese and Mushrooms";
    }

    @Override
    public double cost() {
        return 8.99 + 1.50 + 1.25;
    }
}

class PepperoniWithCheese extends Pizza {
    public PepperoniWithCheese() {
        description = "Pepperoni Pizza with Extra Cheese";
    }

    @Override
    public double cost() {
        return 10.99 + 1.50;
    }
}

class PepperoniWithOlives extends Pizza {
    public PepperoniWithOlives() {
        description = "Pepperoni Pizza with Olives";
    }

    @Override
    public double cost() {
        return 10.99 + 1.00;
    }
}

