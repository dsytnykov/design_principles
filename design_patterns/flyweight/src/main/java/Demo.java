import java.util.List;

public class Demo {
    public static void main(String[] args) {
        GameObjectFactory factory = new GameObjectFactory();
        List<String> players = List.of("player1", "player2");

        players.forEach(player -> factory.getGameObject("tree").display(player));
        players.forEach(player -> factory.getGameObject("rock").display(player));
    }
}
