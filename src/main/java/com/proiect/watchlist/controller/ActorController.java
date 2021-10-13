package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.service.actor.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * Works
     */
    @GetMapping("/actors")
    public List<Actor> listAllActors() {
        return actorService.listAllActors();
    }

    /**
     * Works
     */
    @GetMapping("/actors/{id}")
    public Optional<Actor> getActorById(@PathVariable("id") Integer id) {
        return actorService.getActorById(id);
    }

    /**
     * Works
     */
    @DeleteMapping("/actors/{id}")
    public int deleteActor(@PathVariable("id") int id) {
        return actorService.deleteActor(id);
    }

    /**
     * Works
     */
    @PostMapping("/actors")
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    /**
     * Works
     */
    @PutMapping("actors/{id}")
    public Actor updateActor(@PathVariable("id") int id, @RequestBody Actor actor) {
        return actorService.updateActor(id, actor);
    }

}
