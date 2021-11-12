package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.ActorRepository;
import com.proiect.watchlist.dao.repository.CinemaRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;

    public void addCinemaToMovie(Integer movieId, Integer cinemaId) {

        Movie movie = movieRepository.findById(movieId).get();
        Cinema cinema = cinemaRepository.findById(cinemaId).get();
        movie.addCinema(cinema);
        cinema.addMovie(movie);

        movieRepository.save(movie);
        cinemaRepository.save(cinema);
    }

    public void addActorToMovie(Integer actorId, Integer movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        Actor actor = actorRepository.findById(actorId).get();
        movie.addActors(actor);
        actor.addMovie(movie);

        movieRepository.save(movie);
        actorRepository.save(actor);
    }

    public void saveOrUpdate(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Integer id) {
        return movieRepository.findById(id).get();
    }

    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Actor> findActorsByMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        return movie.getActors();
    }

    public List<Cinema> findCinemasByMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        return movie.getCinemas();
    }
}
