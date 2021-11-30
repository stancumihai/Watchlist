package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.ActorRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.exception.ApiRequestException;
import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Transactional
    public Actor findById(Integer id) {
        return actorRepository.findById(id).
                orElseThrow(() -> new ApiRequestException("Cannot find actor with id : " + id));
    }

    @Transactional
    public Actor saveOrUpdate(Actor actor) {
        return actorRepository.save(actor);
    }

    @Transactional
    public void addMovieToActor(Integer actorId, Integer movieId) {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow(() -> new ApiRequestException("Cannot find movie with id : " + movieId));
        Actor actor = actorRepository.findById(actorId).
                orElseThrow(() -> new ApiRequestException("Cannot find actor with id : " + actorId));
        for(Actor actor1:movie.getActors()){
            if(actor1.equals(actor)){
                throw new ApiRequestException("Actor with id : " + actorId + " already exists");
            }
        }
        for(Movie movie1:actor.getMovies()){
            if(movie1.equals(movie)){
                throw new ApiRequestException("Movie with id : " + movieId + " already exists");
            }
        }
        movie.addActor(actor);
        actor.addMovie(movie);
    }

    @Transactional
    public List<Movie> getMoviesByActor(Integer actorId) {
        Actor actor = actorRepository.findById(actorId).
                orElseThrow(() -> new ApiRequestException("Cannot find actor with id : " + actorId));
        return actor.getMovies();
    }
}
