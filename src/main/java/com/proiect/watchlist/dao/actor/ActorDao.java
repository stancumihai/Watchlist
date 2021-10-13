package com.proiect.watchlist.dao.actor;

import com.proiect.watchlist.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorDao {

    List<Actor> listAllActors();

    Optional<Actor> getActorById(Integer id);

    int deleteActor(int id);

    Actor createActor(Actor actor);

    Actor updateActor(int id, Actor actor);

}
