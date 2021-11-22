package com.proiect.watchlist.dao.repository;

import com.proiect.watchlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.user_name = ?1 and u.password = ?2")
    User findUserByUsernameAndPassword(String username, String password);

}
