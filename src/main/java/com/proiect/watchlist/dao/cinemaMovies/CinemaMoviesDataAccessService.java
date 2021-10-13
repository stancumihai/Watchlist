package com.proiect.watchlist.dao.cinemaMovies;

import com.proiect.watchlist.model.CinemaMovies;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("CinemaMoviesRepo")
public class CinemaMoviesDataAccessService implements CinemaMoviesDao{
    @Override
    public List<CinemaMovies> listAllCinemaMovies() {
        return null;
    }

    @Override
    public Optional<CinemaMovies> getCinemaMoviesById(Integer id) {
        return Optional.empty();
    }

    @Override
    public int deleteCinemaMovies(int id) {
        return 0;
    }

    @Override
    public CinemaMovies createCinemaMovies(CinemaMovies cinemaMovies) {
        return null;
    }

    @Override
    public CinemaMovies updateCinemaMovies(int id, CinemaMovies cinemaMovies) {
        return null;
    }
}
