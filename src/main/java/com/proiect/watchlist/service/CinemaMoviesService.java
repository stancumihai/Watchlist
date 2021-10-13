package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.cinema.CinemaDao;
import com.proiect.watchlist.dao.cinemaMovies.CinemaMoviesDao;
import com.proiect.watchlist.dao.movie.MovieDao;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.CinemaMovies;
import com.proiect.watchlist.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaMoviesService {

    private CinemaDao cinemaDao;
    private MovieDao movieDao;
    private final CinemaMoviesDao cinemaMoviesDao;

    @Autowired
    public CinemaMoviesService(@Qualifier("CinemaRepo") CinemaDao cinemaDao,
                               @Qualifier("MovieRepo") MovieDao movieDao,
                               @Qualifier("CinemaMoviesRepo") CinemaMoviesDao cinemaMoviesDao) {
        this.cinemaDao = cinemaDao;
        this.movieDao = movieDao;
        this.cinemaMoviesDao = cinemaMoviesDao;
    }

    private List<Movie> listAllMoviesFromCinema() {
        return null;
    }

    private List<Cinema> listAllCinemasThatHaveThisMovie() {
        return null;
    }

    public List<CinemaMovies> listAllCinemaMovies() {
        return cinemaMoviesDao.listAllCinemaMovies();
    }

    public Optional<CinemaMovies> getCinemaMoviesById(Integer id) {
        return cinemaMoviesDao.getCinemaMoviesById(id);
    }

    public int deleteCinemaMovies(int id) {
        return cinemaMoviesDao.deleteCinemaMovies(id);
    }

    public CinemaMovies createCinemaMovies(CinemaMovies cinemaMovies) {
        return cinemaMoviesDao.createCinemaMovies(cinemaMovies);
    }

    public CinemaMovies updateCinemaMovies(int id, CinemaMovies cinemaMovies) {
        return cinemaMoviesDao.updateCinemaMovies(id, cinemaMovies);
    }
}
