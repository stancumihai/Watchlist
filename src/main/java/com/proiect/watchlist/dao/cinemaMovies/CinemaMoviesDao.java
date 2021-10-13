package com.proiect.watchlist.dao.cinemaMovies;

import com.proiect.watchlist.model.CinemaMovies;

import java.util.List;
import java.util.Optional;

public interface CinemaMoviesDao {

    List<CinemaMovies> listAllCinemaMovies();

    Optional<CinemaMovies> getCinemaMoviesById(Integer id);

    int deleteCinemaMovies(int id);

    CinemaMovies createCinemaMovies(CinemaMovies cinemaMovies);

    CinemaMovies updateCinemaMovies(int id, CinemaMovies cinemaMovies);
}
