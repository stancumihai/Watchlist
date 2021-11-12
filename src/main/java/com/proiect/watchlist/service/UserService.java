package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.UserRepository;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User findByUsernamePassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
