package com.proiect.watchlist.dao.movie;

import com.proiect.watchlist.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao {

    List<Movie> listAllMovies();

    Optional<Movie> getMovieById(Integer id);

    int deleteMovie(int id);

    Movie createMovie(Movie movie);

    Movie updateMovie(int id, Movie movie);
}
