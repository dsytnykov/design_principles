package service;

import model.Theater;
import repository.TheaterRepository;

public class TheaterService {
    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public void addTheater(Theater theater) {
        theaterRepository.save(theater);
    }

    public Theater findById(String id) {
        return theaterRepository.findById(id);
    }
}
