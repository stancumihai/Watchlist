package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.User;
import com.proiect.watchlist.service.RegisterService;
import com.proiect.watchlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final RegisterService registerService;

    @Autowired
    public UserController(UserService userService, RegisterService registerService) {
        this.userService = userService;
        this.registerService = registerService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public Boolean loginUser(@RequestBody User user) {
        if (!registerService.loginUser(user)) {
            createUser(user);
            return true;
        }
        return null;
    }

    /**
     * Works
     */
    @GetMapping("/users")
    public List<User> listAllUsers() {
        return userService.listAllUsers();
    }

    /**
     * Works
     */
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    /**
     * Works
     */
    @DeleteMapping("/users/{id}")
    public int deleteUser(@PathVariable("id") int id) {
        return userService.deleteUser(id);
    }

    /**
     * Works
     */
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * Works
     */
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") int id, @RequestBody User user) {
        System.out.println(user);
        return userService.updateUser(id, user);
    }
}
