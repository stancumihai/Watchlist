package com.proiect.watchlist.dao.repository;

import com.proiect.watchlist.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
