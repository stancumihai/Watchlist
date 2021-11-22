package com.proiect.watchlist.controller;

import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("/")
    public ResponseEntity<Cinema> saveCinema(@RequestBody Cinema cinema) {
        Cinema newCinema = cinemaService.saveOrUpdate(cinema);
        return new ResponseEntity<>(newCinema, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Cinema> findAll() {
        return cinemaService.findAll();
    }

    @GetMapping("/{id}")
    public Cinema findById(@PathVariable("id") Integer id) {
        return cinemaService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cinema> updateCinema(@RequestBody Cinema cinema, @PathVariable("id") Integer id) {
        if (findById(id) != null) {
            Cinema newCinema = new Cinema(
                    id,
                    cinema.getName(),
                    cinema.getCapacity()
            );
            saveCinema(newCinema);
            return new ResponseEntity<>(newCinema, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/actors/{id}")
    public List<Movie> getMovieByCinema(@PathVariable("id") Integer cinemaId) {
        return cinemaService.getMoviesByCinema(cinemaId);
    }
}
