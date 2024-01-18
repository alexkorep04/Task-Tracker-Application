package com.app.Task_Tracker.service;

import com.app.Task_Tracker.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    User getUser(int id);

    void saveUser(User user);

    void deleteUser(int id);

    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    void updateUsersUsername(User user, String newUsername);
    void updateUsersEmail(User user, String newUsername);
    void updateUsersPassword(User user, String newPassword);
    void banUser(int id);
    void unbanUser(int id);
    void resetPassword(int id, String password);
}
