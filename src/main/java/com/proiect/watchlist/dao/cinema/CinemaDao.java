package com.proiect.watchlist.dao.cinema;

import com.proiect.watchlist.model.Cinema;

import java.util.List;
import java.util.Optional;

public interface CinemaDao {

    List<Cinema> listAllCinemas();

    Optional<Cinema> getCinemaById(Integer id);

    int deleteCinema(int id);

    Cinema createCinema(Cinema cinema);

    Cinema updateCinema(int id, Cinema cinema);
}
