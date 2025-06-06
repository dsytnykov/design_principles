public class Rock implements GameObject {
    private final String type;

    public Rock(String type) {
        this.type = type;
    }

    @Override
    public void display(String player) {
        System.out.println("Displaying " + type + " for " + player);
    }
}
