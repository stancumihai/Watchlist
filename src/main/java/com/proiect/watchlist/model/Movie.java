package com.proiect.watchlist.model;

public class Movie {

    private Integer id;
    private String name;
    private Integer year;
    private String description;
    private String genre;
    private String director;
    private String language;

    public Movie() {
    }

    public Movie(Integer id, String name, Integer year, String description, String genre, String director, String language) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.genre = genre;
        this.director = director;
        this.language = language;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
