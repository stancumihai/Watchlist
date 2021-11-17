package com.proiect.watchlist.controller;

import com.proiect.watchlist.exception.ApiRequestException;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.User;
import com.proiect.watchlist.service.RegisterService;
import com.proiect.watchlist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RegisterService registerService;

    @Autowired
    public UserController(UserService userService, RegisterService registerService) {
        this.userService = userService;
        this.registerService = registerService;
    }

    @PostMapping("/login")
    public Boolean loginUser(@RequestBody User user) {
        if (!registerService.loginUser(user)) {
            saveUser(user);
            return true;
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.saveOrUpdateUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/movies/{id}")
    public List<Movie> getMovies(@PathVariable("id") Integer id) {
        return userService.getMovies(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
        if (findById(id) != null) {
            User newUser = new User(id, user.getUser_name(), user.getPassword(), user.getName(), user.getSurname(), user.getPassword());
            saveUser(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        throw new ApiRequestException("Cannot update this user");
    }
}
