package com.proiect.watchlist.controller;

import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    /**
     * TODO make it work
     */
    @PostMapping("/")
    public void saveCinema(@RequestBody Cinema cinema) {
        cinemaService.saveOrUpdate(cinema);
    }

    /**
     * It Works
     */
    @GetMapping
    public List<Cinema> findAll() {
        return cinemaService.findAll();
    }

    /**
     * It Works
     */
    @GetMapping("/{id}")
    public Cinema findById(@PathVariable("id") Integer id) {
        return cinemaService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateCinema(@RequestBody Cinema cinema) {
        cinemaService.saveOrUpdate(cinema);
    }

    @GetMapping("/actors/{id}")
    public List<Movie> getMovieByCinema(@PathVariable("id") Integer cinemaId) {
        return cinemaService.getMoviesByCinema(cinemaId);
    }
}
