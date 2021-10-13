package com.proiect.watchlist.service;


import com.proiect.watchlist.dao.cinema.CinemaDao;
import com.proiect.watchlist.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    private final CinemaDao cinemaDao;

    @Autowired
    public CinemaService(CinemaDao cinemaDao) {
        this.cinemaDao = cinemaDao;
    }

    @Transactional
    public List<Cinema> listAllCinemas() {
        return cinemaDao.listAllCinemas();
    }

    @Transactional
    public Optional<Cinema> getCinemaById(Integer id) {
        return cinemaDao.getCinemaById(id);
    }

    @Transactional
    public int deleteCinema(int id) {
        return cinemaDao.deleteCinema(id);
    }

    @Transactional
    public Cinema createCinema(Cinema cinema) {

        return cinemaDao.createCinema(cinema);
    }

    @Transactional
    public Cinema updateCinema(int id, Cinema cinema) {
        return cinemaDao.updateCinema(id, cinema);
    }
}
