package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.CinemaRepository;
import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private MovieRepository movieRepository;

    public void saveOrUpdate(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema findById(Integer id) {
        return cinemaRepository.findById(id).get();
    }

    public void addMovieToCinema(Integer movieId, Integer cinemaId) {

        Movie movie = movieRepository.findById(movieId).get();
        Cinema cinema = cinemaRepository.findById(cinemaId).get();
        movie.addCinema(cinema);
        cinema.addMovie(movie);

        movieRepository.save(movie);
        cinemaRepository.save(cinema);
    }

    public List<Movie> getMoviesByCinema(Integer cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).get();
        return cinema.getMovies();
    }
}
