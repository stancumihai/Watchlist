package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.User;
import com.proiect.watchlist.service.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /** Works */
    @GetMapping("/movies")
    public List<Movie> listAllMovies() {
        return movieService.listAllMovies();
    }

    /** Works */
    @GetMapping("/movies/{id}")
    public Optional<Movie> getUserById(@PathVariable("id") Integer id) {
        return movieService.getMovieById(id);
    }

    /** Works */
    @DeleteMapping("/movies/{id}")
    public int deleteMovie(@PathVariable("id") int id) {
        return movieService.deleteMovie(id);
    }

    /** Works */
    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    /** Works */
    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }
}
