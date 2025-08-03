package service;

import model.Movie;
import repository.MovieRepository;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie findMovieById(String id) {
        return movieRepository.findById(id);
    }
}
