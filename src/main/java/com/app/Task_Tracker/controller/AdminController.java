package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.entity.User;
import com.app.Task_Tracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tracker")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin-page")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        List<User> notBannedUsers = users.stream().filter(User::isEnabled).toList();
        List<User> bannedUsers = users.stream().filter(user -> !user.isEnabled()).toList();
        model.addAttribute("users", notBannedUsers);
        model.addAttribute("bannedUsers", bannedUsers);
        return "admin-page";
    }

    @PostMapping("/admin-page/ban/{userId}")
    public String banUser(@PathVariable String userId) {
        userService.banUser(Integer.parseInt(userId));
        return "redirect:/tracker/admin-page";
    }

    @PostMapping("/admin-page/unban/{userId}")
    public String unbanUser(@PathVariable String userId) {
        userService.unbanUser(Integer.parseInt(userId));
        return "redirect:/tracker/admin-page";
    }
}
