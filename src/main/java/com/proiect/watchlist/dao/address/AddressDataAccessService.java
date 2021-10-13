package com.proiect.watchlist.dao.address;

import com.proiect.watchlist.mapper.ActorRowMapper;
import com.proiect.watchlist.mapper.AddressRowMapper;
import com.proiect.watchlist.model.Actor;
import com.proiect.watchlist.model.Address;
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

@Repository("AddressRepo")
public class AddressDataAccessService implements AddressDao {


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AddressDataAccessService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Address> listAllAddresses() {
        String sql = "Select * from address";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }

    @Override
    public Optional<Address> getAddressById(Integer id) {
        String sql = "Select * from address where id = ?";
        Address address = jdbcTemplate.queryForObject(sql, new AddressRowMapper(), id);
        assert address != null;
        return Optional.of(address);
    }

    @Override
    public int deleteAddress(int id) {
        String sql = "Delete from address where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Address createAddress(Address address) {
        String sql = "insert into address (city, country, actorId) " +
                "VALUES (:city, :country, :actorId)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("city", address.getCity())
                .addValue("country", address.getCountry())
                .addValue("actorId", address.getActorId().getId());
        namedParameterJdbcTemplate.update(sql, parameters, holder);
        address.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return address;
    }

    @Override
    public Address updateAddress(int id, Address address) {
        String sql = "update address set city = ?, country = ?,actorId = ?" +
                " where id = ?";
        jdbcTemplate.update(sql, address.getCity(), address.getCountry(),
                address.getActorId().getId(), address.getId());
        address.setId(id);
        return address;
    }
}
