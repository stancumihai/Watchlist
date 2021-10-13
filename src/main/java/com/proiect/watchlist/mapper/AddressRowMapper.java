package com.proiect.watchlist.mapper;

import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setId(rs.getInt("id"));
        address.setCity(rs.getString("city"));
        address.setCountry(rs.getString("country"));
        Actor actor = new Actor();
        actor.setId(rs.getInt("actorId"));
        address.setActorId(actor);
        return address;
    }
}
