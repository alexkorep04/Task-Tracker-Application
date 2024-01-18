package com.app.Task_Tracker.service;

import com.app.Task_Tracker.entity.User;
import com.app.Task_Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        User admin = userRepository.findByUsername("admin");
        List<User> users = userRepository.findAll();
        if(admin != null) {
            users.remove(admin);
        }
        return users;
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void updateUsersUsername(User user, String newUsername) {
        user.setUsername(newUsername);
        userRepository.save(user);
    }

    @Override
    public void updateUsersEmail(User user, String email) {
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updateUsersPassword(User user, String password) {
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void banUser(int id) {
        userRepository.banUser(id);
    }

    @Override
    public void unbanUser(int id) {
        userRepository.unbanUser(id);
    }
    @Override
    public void resetPassword(int id, String password) {
        userRepository.resetPassword(id, password);
    }
}
