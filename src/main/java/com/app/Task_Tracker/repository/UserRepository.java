package com.app.Task_Tracker.repository;

import com.app.Task_Tracker.entity.CompletedTask;
import com.app.Task_Tracker.entity.Type;
import com.app.Task_Tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    @Query("UPDATE User u set u.enabled= false where u.id = :id")
    @Modifying
    @Transactional
    void banUser(@Param("id") int id);

    @Query("UPDATE User u set u.enabled= true where u.id = :id")
    @Modifying
    @Transactional
    void unbanUser(@Param("id") int id);

    @Query("UPDATE User u set u.password= :password where u.id = :id")
    @Modifying
    @Transactional
    void resetPassword(@Param("id") int id, @Param("password") String password);
}
