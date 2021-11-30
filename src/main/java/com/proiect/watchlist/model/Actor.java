package com.proiect.watchlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "actor")
public class Actor {

    @Id
    @SequenceGenerator(
            name = "actor_sequence",
            sequenceName = "actor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "actor_sequence"
    )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "description")
    private String description;

    @Column(name = "URL")
    private String URL;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(
                    name = "actor_Id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "movie_Id",
                    referencedColumnName = "id"
            )
    )

    @JsonIgnore
    private List<Movie> movies;

    public Actor(Integer id, String name, String surname, Date birthdate, String description, String URL) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.description = description;
        this.URL = URL;
    }

    public void addMovie(Movie movie) {
        if (movies == null)
            movies = new ArrayList<>();
        else {
            movies.add(movie);
        }
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", description='" + description + '\'' +
                ", URL='" + URL + '\'' +
                ", movies=" + movies +
                '}';
    }
}
