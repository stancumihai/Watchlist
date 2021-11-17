package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.UserRepository;
import com.proiect.watchlist.exception.ApiRequestException;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Cannot find user with id: " + id));
    }

    @Transactional
    public User findByUsernamePassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Transactional
    public List<Movie> getMovies(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Cannot find user with id: " + id));
        return user.getMovies();
    }
}
