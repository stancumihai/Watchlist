package com.proiect.watchlist.dao.cinema;


import com.proiect.watchlist.mapper.CinemaRowMapper;
import com.proiect.watchlist.mapper.MovieRowMapper;
import com.proiect.watchlist.model.Cinema;
import com.proiect.watchlist.model.Movie;
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

@Repository("CinemaRepo")
public class CinemaDataAccessService implements CinemaDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CinemaDataAccessService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Cinema> listAllCinemas() {
        String sql = "Select * from cinema";
        return jdbcTemplate.query(sql, new CinemaRowMapper());
    }

    @Override
    public Optional<Cinema> getCinemaById(Integer id) {
        String sql = "Select * from cinema where id = ?";
        Cinema cinema = jdbcTemplate.queryForObject(sql, new CinemaRowMapper(), id);
        assert cinema != null;
        return Optional.of(cinema);
    }

    @Override
    public int deleteCinema(int id) {
        String sql = "Delete from cinema where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Cinema createCinema(Cinema cinema) {
        String sql = "insert into cinema (name, capacity) VALUES (:name, :capacity)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", cinema.getName())
                .addValue("capacity", cinema.getCapacity());
        namedParameterJdbcTemplate.update(sql, parameters, holder);
        cinema.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return cinema;
    }

    @Override
    public Cinema updateCinema(int id, Cinema cinema) {
        String sql = "update cinema set name = ?, capacity = ? where id = ?";
        int updated = jdbcTemplate.update(sql, cinema.getName(), cinema.getCapacity(), cinema.getId());
        if (updated == 1) {
            return cinema;
        } else return null;
    }
}
