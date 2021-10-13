package com.proiect.watchlist.dao.actor;

import com.proiect.watchlist.mapper.ActorRowMapper;
import com.proiect.watchlist.model.Actor;
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

@Repository("ActorRepo")
public class ActorDataAccessService implements ActorDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ActorDataAccessService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Actor> listAllActors() {
        String sql = "Select * from actor";
        return jdbcTemplate.query(sql, new ActorRowMapper());
    }

    @Override
    public Optional<Actor> getActorById(Integer id) {
        String sql = "Select * from actor where id = ?";
        Actor actor = jdbcTemplate.queryForObject(sql, new ActorRowMapper(), id);
        assert actor != null;
        return Optional.of(actor);
    }

    @Override
    public int deleteActor(int id) {
        String sql = "Delete from actor where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Actor createActor(Actor actor) {
        String sql = "insert into actor (name, surname, birthdate) " +
                "VALUES (:name, :surname, :birthdate)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", actor.getName())
                .addValue("surname", actor.getSurname())
                .addValue("birthdate", actor.getBirthdate());
        namedParameterJdbcTemplate.update(sql, parameters, holder);
        actor.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return actor;
    }

    @Override
    public Actor updateActor(int id, Actor actor) {
        String sql = "update actor set name = ?, surname = ?,birthdate = ?" +
                " where id = ?";
        jdbcTemplate.update(sql, actor.getName(), actor.getSurname(),
                actor.getBirthdate(), actor.getId());
        actor.setId(id);
        return actor;
    }
}
