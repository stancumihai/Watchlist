package com.proiect.watchlist.dao.repository;

import com.proiect.watchlist.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT u FROM Movie u WHERE u.name LIKE ?1")
    List<Movie> findByTitle(String title);




}
