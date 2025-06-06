import java.util.HashMap;
import java.util.Map;

public class GameObjectFactory {
    private final Map<String, GameObject> objects = Map.of(
            "tree", new Tree("tree"),
            "rock", new Rock("rock"));

    GameObject getGameObject(String type) {
        return objects.getOrDefault(type, (t) -> { throw new IllegalArgumentException("Unknown type: " + t); } );
    }
}
