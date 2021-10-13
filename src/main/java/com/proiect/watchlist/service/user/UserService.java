package com.proiect.watchlist.service.user;

import com.proiect.watchlist.dao.UserDao;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> listAllUsers() {
        return userDao.listAllUsers();
    }

    public Optional<User> getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    public User updateUser(int id, User user) {
        System.out.println(user);
        return userDao.updateUser(id, user);
    }

    public User createUser(User user) {
        return userDao.createUser(user);
    }
}
