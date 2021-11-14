package com.proiect.watchlist.service;


import com.proiect.watchlist.dao.repository.UserRepository;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private final UserRepository userDao;

    @Autowired
    public RegisterService(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public Boolean loginUser(User user) {
        System.out.println(2);
        for (User myUser : userDao.findAll()) {
            if (user.equals(myUser)) {
                System.out.println(1);
                return true;
            }
        }
        return false;
    }
}