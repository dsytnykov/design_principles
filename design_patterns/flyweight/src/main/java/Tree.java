public class Tree implements GameObject {
    private final String type;

    public Tree(String type) {
        this.type = type;
    }

    @Override
    public void display(String player) {
        System.out.println("Displaying " + type + " for " + player);
    }
}
