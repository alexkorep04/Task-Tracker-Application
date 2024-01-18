package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.entity.User;
import com.app.Task_Tracker.service.MailSender;
import com.app.Task_Tracker.service.UserService;
import com.app.Task_Tracker.utils.RandomPassword;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tracker")
public class LoginController {
    private final UserService userService;
    private final MailSender mailSender;

    @Autowired
    public LoginController(UserService userService, MailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        User userFromDb = userService.findByUsernameAndPassword(username, password);
        if ("admin".equals(username) && userFromDb != null) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return "redirect:/tracker/admin-page";
        }
        if (userFromDb != null && userFromDb.isEnabled()) {
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            return "redirect:/tracker/dashboard";
        } else {
            redirectAttributes.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/forgot-password")
    public String getForgotPasswordForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String postForgotPassword(@ModelAttribute("user") User user) {
        User userInDB = userService.findByUsername(user.getUsername());
        if (userInDB != null && userInDB.getEmail().equals(user.getEmail())) {
            String newPassword = RandomPassword.getRandomPassword();
            System.out.println(newPassword);
            userService.resetPassword(userInDB.getId(), newPassword);
            mailSender.send(userInDB.getEmail(), "Reset password on task tracker", "Your new password is " + newPassword + " for account" + userInDB.getUsername() + "\nIf you want to change it you can do this in your profile!\nIf it's not you, write to trackeralexkorep@yandex.ru.");
            return "redirect:/tracker/after-reset";
        }
        return "forgot-password";
    }

    @GetMapping("/after-reset")
    public String getPageAfterReset() {
        return "after-reset";
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession, HttpServletResponse response) {
        httpSession.invalidate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        return "redirect:/tracker/login";
    }
}