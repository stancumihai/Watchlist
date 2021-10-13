package com.proiect.watchlist.controller;


import com.proiect.watchlist.dao.UserDao;
import com.proiect.watchlist.model.User;
import com.proiect.watchlist.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public int deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    //TODO Fix this when you can

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println(user);
        return userService.updateUser(id, user);
    }
}
