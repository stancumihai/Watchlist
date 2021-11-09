package com.proiect.watchlist.dao.user;


import com.proiect.watchlist.mapper.UserRowMapper;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

@Repository("UserRepo")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> listAllUsers() {
        String sql = "Select * from user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        String sql = "Select * from user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        assert user != null;
        return Optional.of(user);
    }

    @Override
    public int deleteUser(int id) {
        String sql = "Delete from user where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public User createUser(User user) {
        String sql = "insert into user (user_name, password) VALUES (:username, :password)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", user.getUser_name())
                .addValue("password", user.getPassword());


        int update = namedParameterJdbcTemplate.update(sql, parameters, holder);
        if (update == 1) {
            user.setId(Objects.requireNonNull(holder.getKey()).intValue());
            return user;
        } else return null;
    }

    @Override
    public User updateUser(int id, User user) {
        String sql = "update user set user_name = ?, password = ? where id = ?";
        jdbcTemplate.update(sql, user.getUser_name(), user.getPassword(), user.getId());
        user.setId(id);
        return user;
    }
}
