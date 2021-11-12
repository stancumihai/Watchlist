package com.proiect.watchlist.controller;

import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * TODO make it work
     */
    @PostMapping("/")
    public void saveOrUpdate(@RequestBody Movie movie) {
        movieService.saveOrUpdate(movie);
    }

    /**
     * It Works
     */
    @GetMapping
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    /**
     * It Works
     */
    @GetMapping("/{id}")
    public Movie findById(@PathVariable Integer id) {
        return movieService.findById(id);
    }

    /**
     * It Works
     */
    @GetMapping("/title/{title}")
    public List<Movie> getMovieByTitle(@PathVariable("title") String title) {
        return movieService.findByTitle(title);
    }

    /**
     * It Works
     */
    @PutMapping("/")
    public void updateMovie(@RequestBody Movie movie) {
        movieService.saveOrUpdate(movie);
    }

    @GetMapping("/actors/{id}")
    public List<Actor> getActorsByMovie(@PathVariable("id") Integer movieId) {
        return movieService.findActorsByMovie(movieId);
    }

    @GetMapping("/movies/{id}")
    public List<Cinema> getCinemasByMovie(@PathVariable("id") Integer movieId) {
        return movieService.findCinemasByMovie(movieId);
    }
}
