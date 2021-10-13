package com.proiect.watchlist.dao.movie;

import com.proiect.watchlist.mapper.MovieRowMapper;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("MovieRepo")
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public MovieDataAccessService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Movie> listAllMovies() {
        String sql = "Select * from movie";
        return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public Optional<Movie> getMovieById(Integer id) {
        String sql = "Select * from movie where id = ?";
        Movie movie = jdbcTemplate.queryForObject(sql, new MovieRowMapper(), id);
        assert movie != null;
        return Optional.of(movie);
    }

    @Override
    public int deleteMovie(int id) {
        String sql = "Delete from movie where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Movie createMovie(Movie movie) {
        String sql = "insert into movie (name, year," +
                "genre, director, language) VALUES (:name, :year," +
                ":genre, :director, :language)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", movie.getName())
                .addValue("year", movie.getYear())
                .addValue("genre", movie.getGenre())
                .addValue("director", movie.getDirector())
                .addValue("language", movie.getLanguage());
        namedParameterJdbcTemplate.update(sql, parameters, holder);
        movie.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return movie;
    }

    @Override
    public Movie updateMovie(int id, Movie movie) {
        String sql = "update movie set name = ?, year = ?" +
                ", genre = ?, director = ? , language = ? where id = ?";
        int updated = jdbcTemplate.update(sql, movie.getName(), movie.getYear(), movie.getGenre(),
                movie.getDirector(), movie.getLanguage(), movie.getId());
        if (updated == 1) {
            return movie;
        } else return null;
    }
}
