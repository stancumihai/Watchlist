package com.proiect.watchlist.dao.movieActors;

import com.proiect.watchlist.model.MovieActors;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("MovieActorsRepo")
public class MovieActorDataAccessService implements MovieActorsDao {
    @Override
    public List<MovieActors> listAllMovieActors() {
        return null;
    }

    @Override
    public Optional<MovieActors> getMovieActorsById(Integer id) {
        return Optional.empty();
    }

    @Override
    public int deleteActor(int id) {
        return 0;
    }

    @Override
    public MovieActors createMovieActors(MovieActors movieActors) {
        return null;
    }

    @Override
    public MovieActors updateMovieActors(int id, MovieActors movieActors) {
        return null;
    }
}
