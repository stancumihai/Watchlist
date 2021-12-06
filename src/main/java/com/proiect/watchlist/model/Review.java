package com.proiect.watchlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "review")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "review")
public class Review {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private Integer id;

    @Column(name = "body")
    private String body;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Review(String body, Integer rating, User user, Movie movie) {
        this.body = body;
        this.rating = rating;
        this.user = user;
        this.movie = movie;
    }

    @Override
    public String
    toString() {
        return "Review{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                ", movie=" + movie +
                '}';
    }
}