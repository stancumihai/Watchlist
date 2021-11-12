package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.service.ActorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * TODO make it work
     */
    @PostMapping("/")
    public void saveActor(@RequestBody Actor actor) {
        actorService.saveOrUpdate(actor);
    }

    /**
     * It Works
     */
    @GetMapping
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    /**
     * It Works
     */
    @GetMapping("/{id}")
    public Actor findAllById(@PathVariable("id") Integer id) {
        return actorService.findById(id);
    }

    @GetMapping("/cinemas")
    public List<Movie> getMoviesByActor(Integer actorId) {
        return actorService.getMoviesByActor(actorId);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Actor actor) {
        actorService.saveOrUpdate(actor);
    }
}
