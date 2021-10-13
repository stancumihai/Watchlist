package com.proiect.watchlist.service.user;

import com.proiect.watchlist.dao.user.UserDao;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("UserRepo") UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public List<User> listAllUsers() {
        return userDao.listAllUsers();
    }
    @Transactional
    public Optional<User> getUserById(Integer id) {
        return userDao.getUserById(id);
    }
    @Transactional
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
    @Transactional
    public User updateUser(int id, User user) {
        System.out.println(user);
        return userDao.updateUser(id, user);
    }
    @Transactional
    public User createUser(User user) {
        return userDao.createUser(user);
    }
}
