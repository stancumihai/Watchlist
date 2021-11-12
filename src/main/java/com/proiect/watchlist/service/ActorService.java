package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.ActorRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;

    public Actor findById(Integer id) {
        return actorRepository.findById(id).get();
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public void saveOrUpdate(Actor actor) {
        actorRepository.save(actor);
    }

    public void addMovieToActor(Integer actorId, Integer movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        Actor actor = actorRepository.findById(actorId).get();
        movie.addActors(actor);
        actor.addMovie(movie);

        movieRepository.save(movie);
        actorRepository.save(actor);
    }

    public List<Movie> getMoviesByActor(Integer actorId) {
        Actor actor = actorRepository.findById(actorId).get();
        return actor.getMovies();
    }

}
