package com.proiect.watchlist.controller;

import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.Review;
import com.proiect.watchlist.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/")
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        Movie newMovie = movieService.saveOrUpdate(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable Integer id) {
        return movieService.findById(id);
    }

    @GetMapping("/title/{title}")
    public List<Movie> getMovieByTitle(@PathVariable("title") String title) {
        return movieService.findByTitle(title);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable("id") Integer id) {
        if (findById(id) != null) {
            Movie newMovie = new Movie(id,
                    movie.getName(),
                    movie.getYear(),
                    movie.getGenre(),
                    movie.getDirector(),
                    movie.getLanguage(),
                    movie.getDescription(),
                    movie.getURL()
            );
            save(newMovie);
            return new ResponseEntity<>(newMovie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/actors/{id}")
    public List<Actor> getActorsByMovie(@PathVariable("id") Integer movieId) {
        return movieService.findActorsByMovie(movieId);
    }

    @GetMapping("/movies/{id}")
    public List<Cinema> getCinemasByMovie(@PathVariable("id") Integer movieId) {
        return movieService.findCinemasByMovie(movieId);
    }

    @GetMapping("/reviews/{id}")
    public List<Review> getMovieReviews(@PathVariable("id") Integer movieId) {
        return movieService.getMovieReviews(movieId);
    }
}
