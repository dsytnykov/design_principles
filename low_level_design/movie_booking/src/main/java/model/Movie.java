package model;

public class Movie {
    private final String id;
    private final String name;
    private final String genre;
    private final int duration;

    public Movie(String id, String name, String genre, int duration) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }
}
