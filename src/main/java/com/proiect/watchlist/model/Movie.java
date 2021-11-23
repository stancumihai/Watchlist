package com.proiect.watchlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "movie_sequence"
    )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "language")
    private String language;

    @Column(name = "description")
    private String description;
    @Column(name = "URL")
    private String URL;

    public Movie(Integer id, String name, Integer year, String genre, String director, String language, String description, String URL) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.language = language;
        this.description = description;
        this.URL = URL;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "cinema_movies",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cinema_id",
                    referencedColumnName = "id"
            )
    )
    @JsonIgnore
    private List<Cinema> cinemas;

    public void addCinema(Cinema cinema) {
        if (cinemas == null)
            cinemas = new ArrayList<>();
        cinemas.add(cinema);

    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "actor_id",
                    referencedColumnName = "id"
            )
    )
    @JsonIgnore
    private List<Actor> actors;

    public void addActor(Actor actor) {
        if (actors == null)
            actors = new ArrayList<>();
        actors.add(actor);
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "user_movies",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )
    )
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy="review",orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
