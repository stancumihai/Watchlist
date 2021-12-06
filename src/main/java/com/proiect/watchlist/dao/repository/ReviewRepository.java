package com.proiect.watchlist.dao.repository;

import com.proiect.watchlist.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review , Integer> {

    @Query(value = "SELECT AVG(r.rating) FROM Review r WHERE r.movie_id=?1", nativeQuery = true)
    Float getMovieRating(Integer id);
}

