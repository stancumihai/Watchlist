package com.proiect.watchlist.service;
import com.proiect.watchlist.dao.repository.UserRepository;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userDao;

    @Transactional
    public Boolean loginUser(User user) {

        for (User myUser : userDao.findAll()) {
            if (user.equals(myUser)) {
                return true;
            }
        }
        return false;
    }
}