package com.proiect.watchlist.mapper;

import com.proiect.watchlist.model.Actor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorRowMapper implements RowMapper<Actor> {
    @Override
    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Actor actor = new Actor();
        actor.setId(rs.getInt("id"));
        actor.setName(rs.getString("name"));
        actor.setSurname(rs.getString("surname"));
        actor.setBirthdate(rs.getDate("birthdate"));
        return actor;
    }
}
