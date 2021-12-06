package com.proiect.watchlist.service;

import com.proiect.watchlist.dao.repository.MovieRepository;
import com.proiect.watchlist.dao.repository.ReviewRepository;
import com.proiect.watchlist.dao.repository.UserRepository;
import com.proiect.watchlist.exception.ApiRequestException;
import com.proiect.watchlist.model.Movie;
import com.proiect.watchlist.model.Review;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository,
                         MovieRepository movieRepository) {

        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Transactional
    public Review findById(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Cannot find review with id: " + id));
    }

    @Transactional
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    public Review saveOrUpdateReview(Review review, Integer userId, Integer movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("Cannot find user with id: " + userId));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ApiRequestException("Cannot find movie with id: " + movieId));
        Review review1 = new Review(review.getBody(), review.getRating(), userRepository.findById(userId).get(),
                movieRepository.findById(movieId).get());

        saveReview(review1);

        userRepository.findById(userId).get().addReview(review1);
        return review1;
    }

    public Float getMovieRating(Integer id) {
        return reviewRepository.getMovieRating(id);
    }
}
