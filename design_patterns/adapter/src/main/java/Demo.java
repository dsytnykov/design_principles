public class Demo {
    public static void main(String[] args) {
        Target target = new SpecificTarget();
        target.request();

        System.out.println();
        Adaptee adaptee = new SpecificAdaptee();
        Target targetAdapter = new Adapter(adaptee);
        targetAdapter.request();

    }
}
