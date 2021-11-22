package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.dao.repository.UserRepository;
import com.proiect.watchlist.exception.ApiRequestException;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public UserService(UserRepository userRepository, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
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
    public Movie addMovieToUser(Integer userId, Integer movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("Cannot find user with id: " + userId));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ApiRequestException("Cannot find user with id: " + movieId));
        for(Movie theMovie : user.getMovies()){
            if(theMovie.getId().equals(movieId)){
                throw new ApiRequestException("Cannot find user with id: " + movieId);
            }
        }
        return userRepository.findById(userId).get().addMovie(movie);
    }

    @Transactional
    public List<Movie> getMovies(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Cannot find user with id: " + id)).getMovies();
    }
}
