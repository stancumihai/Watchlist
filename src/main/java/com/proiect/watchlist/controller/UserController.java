package com.proiect.watchlist.controller;


import com.proiect.watchlist.model.User;
import com.proiect.watchlist.service.RegisterService;
import com.proiect.watchlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
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
            saveUser(user);
            return true;
        }
        return null;
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
