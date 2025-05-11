public abstract class CaffeineBeverage {
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourWater();
        addCondiments();
        hook();
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourWater() {
        System.out.println("Pouring water");
    }

    void hook() {}
}
