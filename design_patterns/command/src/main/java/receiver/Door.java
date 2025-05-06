package receiver;

public class Door {
    String location;

    public Door(String location) {
        this.location = location;
    }

    public void up() {
        System.out.println(location + " door is up");
    }
    public void down() {
        System.out.println(location + " door is down");
    };
}
