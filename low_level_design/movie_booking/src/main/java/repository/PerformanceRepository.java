package repository;

import model.Performance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformanceRepository {
    private final Map<String, Performance> map = new HashMap<>();

    public void save(Performance performance) {
        map.putIfAbsent(performance.getId(), performance);
    }

    public Performance findById(String id) {
        return map.get(id);
    }

    public List<Performance> findAll() { return map.values().stream().toList(); }
}
