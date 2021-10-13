package com.proiect.watchlist.service.actor;


import com.proiect.watchlist.dao.actor.ActorDao;
import com.proiect.watchlist.model.Actor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    private final ActorDao actorDao;

    public ActorService(@Qualifier("ActorRepo") ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Transactional
    public List<Actor> listAllActors() {
        return actorDao.listAllActors();
    }

    @Transactional
    public Optional<Actor> getActorById(Integer id) {
        return actorDao.getActorById(id);
    }

    @Transactional
    public int deleteActor(int id) {
        return actorDao.deleteActor(id);
    }

    @Transactional
    public Actor createActor(Actor actor) {
        return actorDao.createActor(actor);
    }

    @Transactional
    public Actor updateActor(int id, Actor actor) {
        return actorDao.updateActor(id, actor);
    }
}
