package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.User;
import com.proiect.watchlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * TODO make it work
     */
    @PostMapping("/")
    public void saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }

    /**
     * It Works
     */
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * It Works
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

//    //what does this do?
//    @GetMapping("/id")
//    public User loginUser(String username, String password) {
//        return userService.findByUsernamePassword(username, password);
//    }

    /**
     * It partially works, I need to somehow make so that i do not have to put the id in the body
     */
    @PutMapping("/")
    public User updateUser(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }
}
