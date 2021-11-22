package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.CinemaRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.exception.ApiRequestException;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Cinema saveOrUpdate(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Transactional
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Transactional
    public Cinema findById(Integer id) {
        return cinemaRepository.findById(id).
                orElseThrow(() -> new ApiRequestException("Cannot find cinema with id : " + id));
    }

    @Transactional
    public void addMovieToCinema(Integer movieId, Integer cinemaId) {

        Movie movie = movieRepository.findById(movieId).
                orElseThrow(() -> new ApiRequestException("Cannot find movie with id : " + movieId));
        Cinema cinema = cinemaRepository.findById(cinemaId).
                orElseThrow(() -> new ApiRequestException("Cannot find cinema with id : " + cinemaId));
        movie.addCinema(cinema);
        cinema.addMovie(movie);

        movieRepository.save(movie);
        cinemaRepository.save(cinema);
    }

    @Transactional
    public List<Movie> getMoviesByCinema(Integer cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).
                orElseThrow(() -> new ApiRequestException("Cannot find cinema with id : " + cinemaId));
        return cinema.getMovies();
    }
}
