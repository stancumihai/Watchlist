package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    /**
     * Works
     */
    @GetMapping("/cinemas")
    public List<Cinema> listAllCinemas() {
        return cinemaService.listAllCinemas();
    }

    /**
     * Works
     */
    @GetMapping("/cinemas/{id}")
    public Optional<Cinema> getCinemaById(@PathVariable("id") Integer id) {
        return cinemaService.getCinemaById(id);
    }

    /**
     * Works
     */
    @DeleteMapping("/cinemas/{id}")
    public int deleteCinema(@PathVariable("id") int id) {
        return cinemaService.deleteCinema(id);
    }

    /**
     * Works
     */
    @PostMapping("/cinemas")
    public Cinema createCinema(@RequestBody Cinema cinema) {
        return cinemaService.createCinema(cinema);
    }

    /**
     * Works
     */
    @PutMapping("/cinemas/{id}")
    public Cinema updateCinema(@PathVariable("id") int id, @RequestBody Cinema cinema) {
        return cinemaService.updateCinema(id, cinema);
    }
}
