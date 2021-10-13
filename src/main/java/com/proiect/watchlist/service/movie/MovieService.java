package com.proiect.watchlist.service.movie;


import com.proiect.watchlist.dao.movie.MovieDao;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieDao movieDao;

    @Autowired
    public MovieService(@Qualifier("MovieRepo") MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Transactional
    public List<Movie> listAllMovies() {
        return movieDao.listAllMovies();
    }

    @Transactional
    public Optional<Movie> getMovieById(Integer id) {
        return movieDao.getMovieById(id);
    }

    @Transactional
    public int deleteMovie(int id) {
        return movieDao.deleteMovie(id);
    }

    @Transactional
    public Movie createMovie(Movie movie) {
        return movieDao.createMovie(movie);
    }
    @Transactional
    public Movie updateMovie(int id, Movie movie) {
        return movieDao.updateMovie(id, movie);
    }
}
