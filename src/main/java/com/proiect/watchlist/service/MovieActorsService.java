package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.actor.ActorDao;
import com.proiect.watchlist.dao.movie.MovieDao;
import com.proiect.watchlist.dao.movieActors.MovieActorsDao;
import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.MovieActors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieActorsService {

    private final MovieDao movieDao;
    private final ActorDao actorDao;
    private final MovieActorsDao movieActorsDao;

    @Autowired
    public MovieActorsService(@Qualifier("MovieRepo") MovieDao movieDao,
                              @Qualifier("ActorRepo") ActorDao actorDao,
                              @Qualifier("MovieActorsRepo")
                                      MovieActorsDao movieActorsDao) {
        this.movieDao = movieDao;
        this.actorDao = actorDao;
        this.movieActorsDao = movieActorsDao;
    }

    @Transactional
    public List<Movie> getAllMoviesFromActor(Actor actor) {
        return null;
    }

    @Transactional
    public List<Actor> getAllActorsFromMovie(Movie movie) {
        return null;
    }

    @Transactional
    public List<MovieActors> listAllMovieActors() {
        return movieActorsDao.listAllMovieActors();
    }

    @Transactional
    public Optional<MovieActors> getMovieActorsById(Integer id) {
        return movieActorsDao.getMovieActorsById(id);
    }

    @Transactional
    public int deleteActor(int id) {
        return movieActorsDao.deleteActor(id);
    }

    public MovieActors createMovieActors(MovieActors movieActors) {
        return movieActorsDao.createMovieActors(movieActors);
    }

    public MovieActors updateMovieActors(int id, MovieActors movieActors) {
        return movieActorsDao.updateMovieActors(id, movieActors);
    }
}
