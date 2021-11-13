package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.ActorRepository;
import com.proiect.watchlist.dao.repository.CinemaRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.exception.ResourceNotFoundException;
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
                orElseThrow(() -> new ResourceNotFoundException(Movie.class.getSimpleName(), movieId));
        Cinema cinema = cinemaRepository.findById(cinemaId).
                orElseThrow(() -> new ResourceNotFoundException(Cinema.class.getSimpleName(), movieId));
        movie.addCinema(cinema);
        cinema.addMovie(movie);

        movieRepository.save(movie);
        cinemaRepository.save(cinema);
    }

    public void addActorToMovie(Integer actorId, Integer movieId) {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow(() -> new ResourceNotFoundException(Movie.class.getSimpleName(), movieId));
        Actor actor = actorRepository.findById(actorId).
                orElseThrow(() -> new ResourceNotFoundException(Actor.class.getSimpleName(), movieId));
        movie.addActors(actor);
        actor.addMovie(movie);

        movieRepository.save(movie);
        actorRepository.save(actor);
    }

    public Movie saveOrUpdate(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Movie.class.getSimpleName(), id));
    }

    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Actor> findActorsByMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Movie.class.getSimpleName(), id));
        return movie.getActors();
    }

    public List<Cinema> findCinemasByMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Movie.class.getSimpleName(), id));
        return movie.getCinemas();
    }
}
