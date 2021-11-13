package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * It Works
     */
    @PostMapping("/")
    public ResponseEntity<Actor> saveActor(@RequestBody Actor actor) {
        Actor newActor = actorService.saveOrUpdate(actor);
        return new ResponseEntity<>(newActor, HttpStatus.CREATED);
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
    public Actor findById(@PathVariable("id") Integer id) {
        return actorService.findById(id);
    }

    @GetMapping("/cinemas")
    public List<Movie> getMoviesByActor(Integer actorId) {
        return actorService.getMoviesByActor(actorId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> updateUser(@RequestBody Actor actor, @PathVariable("id") Integer id) {
        if (findById(id) != null) {
            Actor newActor = new Actor(id,
                    actor.getName(),
                    actor.getSurname(),
                    actor.getBirthdate()
            );
            saveActor(newActor);
            return new ResponseEntity<>(newActor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
