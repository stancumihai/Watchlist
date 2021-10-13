package com.proiect.watchlist.mapper;

import com.proiect.watchlist.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUser_name(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
