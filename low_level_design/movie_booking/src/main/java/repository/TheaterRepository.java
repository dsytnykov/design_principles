package repository;

import model.Theater;

import java.util.HashMap;
import java.util.Map;

public class TheaterRepository {
    private final Map<String, Theater> map = new HashMap<>();

    public void save(Theater theater) {
        map.putIfAbsent(theater.getId(), theater);
    }

    public Theater findById(String id) {
        return map.get(id);
    }
}
