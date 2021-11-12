package com.proiect.watchlist.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cinema")
public class Cinema {

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "cinema_sequence",
            sequenceName = "cinema_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "cinema_sequence"
    )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cinema_movies",
            joinColumns = @JoinColumn(
                    name = "cinemaId",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movieId",
                    referencedColumnName = "id"
            )
    )
    @JsonIgnore
    private List<Movie> movies;

    public void addMovie(Movie movie) {
        if (movies == null)
            movies = new ArrayList<>();
        movies.add(movie);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
