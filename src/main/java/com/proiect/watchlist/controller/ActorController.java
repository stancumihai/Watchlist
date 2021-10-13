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

    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public List<Actor> listAllActors() {
        return actorService.listAllActors();
    }

    @GetMapping("/actors/{id}")
    public Optional<Actor> getActorById(@PathVariable("id") Integer id) {
        return actorService.getActorById(id);
    }

    @DeleteMapping("/actors/{id}")
    public int deleteActor(@PathVariable("id") int id) {
        return actorService.deleteActor(id);
    }

    @PostMapping("/actors")
    public Actor createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    @PutMapping("actors/{id}")
    public Actor updateActor(@PathVariable("id") int id, @RequestBody Actor actor) {
        return actorService.updateActor(id, actor);
    }

}
