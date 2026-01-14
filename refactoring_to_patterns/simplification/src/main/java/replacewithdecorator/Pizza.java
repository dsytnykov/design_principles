package replacewithdecorator;

abstract class Pizza {
    protected String description = "Unknown Pizza";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
class MargheritaPizza extends Pizza {
    public MargheritaPizza() {
        description = "Margherita Pizza";
    }

    @Override
    public double cost() {
        return 8.99;
    }
}

class PepperoniPizza extends Pizza {
    public PepperoniPizza() {
        description = "Pepperoni Pizza";
    }

    @Override
    public double cost() {
        return 10.99;
    }
}

