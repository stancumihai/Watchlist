package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.ActorRepository;
import com.proiect.watchlist.dao.repository.CinemaRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.exception.ApiRequestException;
import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieService(CinemaRepository cinemaRepository,
                        MovieRepository movieRepository,
                        ActorRepository actorRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public void addCinemaToMovie(Integer movieId, Integer cinemaId) {

        Movie movie = movieRepository.findById(movieId).
                orElseThrow(() -> new ApiRequestException("Cannot find movie with id : " + movieId));
        Cinema cinema = cinemaRepository.findById(cinemaId).
                orElseThrow(() -> new ApiRequestException("Cannot find cinema with id : " + cinemaId));
        movie.addCinema(cinema);
        cinema.addMovie(movie);

        movieRepository.save(movie);
        cinemaRepository.save(cinema);
    }

    public void addActorToMovie(Integer actorId, Integer movieId) {
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
        //TODO Explica-mi
//        movieRepository.save(movie);
//        actorRepository.save(actor);
    }

    public Movie saveOrUpdate(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Integer id) {
        return movieRepository.findById(id).
                orElseThrow(() -> new ApiRequestException("Cannot find movie with id : " + id));
    }

    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Actor> findActorsByMovie(Integer id) {
        Movie movie = movieRepository.findById(id).
                orElseThrow(() -> new ApiRequestException("Cannot find movie with id : " + id));
        return movie.getActors();
    }

    public List<Cinema> findCinemasByMovie(Integer id) {
        Movie movie = movieRepository.findById(id).
                orElseThrow(() -> new ApiRequestException("Cannot find movie with id : " + id));
        return movie.getCinemas();
    }
}
