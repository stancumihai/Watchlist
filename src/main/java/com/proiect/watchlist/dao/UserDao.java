package com.proiect.watchlist.dao;

import com.proiect.watchlist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> listAllUsers();

    Optional<User> getUserById(Integer id);

    int deleteUser(int id);

    User createUser(User user);

    User updateUser(int id, User user);
}
