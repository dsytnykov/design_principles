package service;

import model.Performance;
import repository.PerformanceRepository;

import java.util.List;

public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public void addPerformance(Performance performance) {
        performanceRepository.save(performance);
    }

    public List<Performance> findPerformanceByName(String name) {
        return performanceRepository.findAll().stream().filter(performance -> performance.getMovie().getName().equals(name)).toList();
    }
}
