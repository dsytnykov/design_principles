package repository;

import model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private final Map<String, Movie> movie = new HashMap<>();

    public void save(Movie movie) {
        this.movie.putIfAbsent(movie.getId(), movie);
    }

    public Movie findById(String id) {
        return movie.get(id);
    }
}
