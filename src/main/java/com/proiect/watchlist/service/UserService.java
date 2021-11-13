package com.proiect.watchlist.service;

import com.proiect.watchlist.exception.ResourceNotFoundException;
import com.proiect.watchlist.dao.repository.UserRepository;
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
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
    }

    @Transactional
    public User findByUsernamePassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
