package com.proiect.watchlist.controller;

import com.proiect.watchlist.model.Review;
import com.proiect.watchlist.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{userId}/{movieId}")
    public ResponseEntity<Review> save(@RequestBody Review review, @PathVariable("userId") Integer userId, @PathVariable("movieId") Integer movieId) {
        Review newReview = reviewService.saveOrUpdateReview(review, userId, movieId);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }


    @GetMapping
    public List<Review> findAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public Review findById(@PathVariable("id") Integer id) {
        return reviewService.findById(id);
    }

    @PostMapping
    public  ResponseEntity<Review> saveReview(@RequestBody Review review) {
        Review newReview = reviewService.saveReview(review);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
}
