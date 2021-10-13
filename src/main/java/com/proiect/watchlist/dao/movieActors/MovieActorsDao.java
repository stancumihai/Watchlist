package com.proiect.watchlist.dao.movieActors;

import com.proiect.watchlist.model.MovieActors;

import java.util.List;
import java.util.Optional;

public interface MovieActorsDao {

    List<MovieActors> listAllMovieActors();

    Optional<MovieActors> getMovieActorsById(Integer id);

    int deleteActor(int id);

    MovieActors createMovieActors(MovieActors movieActors);

    MovieActors updateMovieActors(int id, MovieActors movieActors);
}
