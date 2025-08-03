package model;

import java.util.Map;

public class Theater {
    private final String id;
    private final String name;
    private final Map<String,Room> rooms;

    public Theater(String id, String name, Map<String, Room> rooms) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Room getRoom(String key) {
        return rooms.get(key);
    }

    public void addRoom(Room room) {
        rooms.putIfAbsent(room.getId(), room);
    }
}
