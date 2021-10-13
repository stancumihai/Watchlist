package com.proiect.watchlist.mapper;

import com.proiect.watchlist.model.Cinema;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaRowMapper implements RowMapper<Cinema> {
    @Override
    public Cinema mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cinema cinema = new Cinema();
        cinema.setId(rs.getInt("id"));
        cinema.setName(rs.getString("name"));
        cinema.setCapacity(rs.getInt("capacity"));
        return cinema;
    }
}
