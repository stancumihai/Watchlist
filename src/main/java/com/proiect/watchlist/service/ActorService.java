package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.ActorRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.exception.ResourceNotFoundException;
import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Transactional
    public Actor findById(Integer id) {
        return actorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(Actor.class.getSimpleName(), id));
    }

    @Transactional
    public Actor saveOrUpdate(Actor actor) {
        return actorRepository.save(actor);
    }

    @Transactional
    public void addMovieToActor(Integer actorId, Integer movieId) {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow(() -> new ResourceNotFoundException(Movie.class.getSimpleName(), movieId));
        Actor actor = actorRepository.findById(actorId).
                orElseThrow(() -> new ResourceNotFoundException(Actor.class.getSimpleName(), actorId));
        movie.addActors(actor);
        actor.addMovie(movie);
        movieRepository.save(movie);
        actorRepository.save(actor);
    }

    @Transactional
    public List<Movie> getMoviesByActor(Integer actorId) {
        Actor actor = actorRepository.findById(actorId).
                orElseThrow(() -> new ResourceNotFoundException(Actor.class.getSimpleName(), actorId));

        return actor.getMovies();
    }
}
