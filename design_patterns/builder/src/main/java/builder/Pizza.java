package builder;

public class Pizza {
    private String dough;
    private String sauce;
    private String topping;
    private boolean cheese;
    private boolean pepperoni;
    private boolean mushrooms;

    void setDough(String dough) { this.dough = dough; }
    void setSauce(String sauce) { this.sauce = sauce; }
    void setTopping(String topping) { this.topping = topping; }
    void setCheese(boolean cheese) { this.cheese = cheese; }
    void setPepperoni(boolean pepperoni) { this.pepperoni = pepperoni; }
    void setMushrooms(boolean mushrooms) { this.mushrooms = mushrooms; }

    @Override
    public String toString() {
        return "Pizza{" +
                "dough='" + dough + '\'' +
                ", sauce='" + sauce + '\'' +
                ", topping='" + topping + '\'' +
                ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", mushrooms=" + mushrooms +
                '}';
    }
}
