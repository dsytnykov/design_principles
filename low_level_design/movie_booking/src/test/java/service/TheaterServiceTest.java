package service;

import model.Room;
import model.Theater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TheaterRepository;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TheaterServiceTest {

    private TheaterService theaterService;

    @BeforeEach
    void setUp() {
        theaterService = new TheaterService(new TheaterRepository());
    }

    @Test
    void shouldAddAndFindTheater() {
        Map<String, Room> rooms = Map.of("roomId", new Room("roomId"));

        Theater theater = new Theater("anyId", "anyName", rooms);

        theaterService.addTheater(theater);

        assertNotNull(theaterService.findById(theater.getId()));
    }

}