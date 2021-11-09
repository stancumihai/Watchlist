package com.proiect.watchlist.service;


import com.proiect.watchlist.dao.user.UserDao;
import com.proiect.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public Boolean loginUser(User user) {

        for (User myUser : userDao.listAllUsers()) {
            if (user.equals(myUser)) {
                System.out.println(1);
                return true;
            }
        }
        return false;
    }
}
