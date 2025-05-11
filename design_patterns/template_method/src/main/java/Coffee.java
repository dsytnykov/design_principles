public class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Adding coffee");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
