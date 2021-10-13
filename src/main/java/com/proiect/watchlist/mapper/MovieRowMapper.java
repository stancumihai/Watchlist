package com.proiect.watchlist.mapper;

import com.proiect.watchlist.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("id"));
        movie.setName(rs.getString("name"));
        movie.setYear(rs.getInt("year"));
        movie.setDescription(rs.getString("description"));
        movie.setGenre(rs.getString("genre"));
        movie.setDirector(rs.getString("director"));
        movie.setLanguage(rs.getString("language"));
        return movie;
    }
}
