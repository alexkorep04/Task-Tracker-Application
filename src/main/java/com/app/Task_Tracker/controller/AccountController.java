package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.entity.User;
import com.app.Task_Tracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tracker")
public class AccountController {
    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard/check")
    public String getCheckPasswordForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "check";
    }

    @PostMapping("/dashboard/check")
    public String postCheckPassword(HttpSession session, @ModelAttribute User user) {
        String username = (String) session.getAttribute("username");
        User userFromDB = userService.findByUsername(username);
        if (userFromDB == null) {
            return "redirect:/tracker/login";
        }
        if (user.getPassword().equals(userFromDB.getPassword())) {
            return "redirect:/tracker/dashboard/account";
        }
        return "redirect:/tracker/login";
    }

    @GetMapping("/dashboard/account")
    public String getAccountInfo(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User userFromDB = userService.findByUsername(username);
        model.addAttribute("userFromDB", userFromDB);
        return "own_info";
    }

    @GetMapping("/dashboard/account/update-username")
    public String getUpdateUsernameForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "change_username";
    }

    @PostMapping("/dashboard/account/update-username")
    public String postUpdateUsername(@ModelAttribute User user, HttpSession session) {
        String name = (String) session.getAttribute("username");
        String username = user.getUsername();
        User userFromDB = userService.findByUsername(username);
        if(userFromDB == null) {
            User realUser = userService.findByUsername(name);
            userService.updateUsersUsername(realUser, username);
            return "redirect:/tracker/login";
        }
        return "change_username";
    }

    @GetMapping("/dashboard/account/update-email")
    public String getUpdateEmailForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "change_email";
    }

    @PostMapping("/dashboard/account/update-email")
    public String postUpdateEmail(@ModelAttribute User user, HttpSession session) {
        String name = (String) session.getAttribute("username");
        String email = user.getEmail();
        User realUser = userService.findByUsername(name);
        userService.updateUsersEmail(realUser, email);
        return "redirect:/tracker/login";
    }

    @GetMapping("/dashboard/account/update-password")
    public String getUpdatePasswordForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "change_password";
    }

    @PostMapping("/dashboard/account/update-password")
    public String postUpdatePassword(@ModelAttribute User user, HttpSession session) {
        String name = (String) session.getAttribute("username");
        String password = user.getPassword();
        User realUser = userService.findByUsername(name);
        userService.updateUsersPassword(realUser, password);
        return "redirect:/tracker/login";
    }
}
