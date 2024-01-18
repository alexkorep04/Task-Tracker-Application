package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.entity.User;
import com.app.Task_Tracker.service.MailSender;
import com.app.Task_Tracker.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/tracker")
public class RegistrationController {
    private final UserService userService;
    private final MailSender mailSender;

    @Autowired
    public RegistrationController(UserService userService, MailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }


    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("error") String error) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("error", error);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            redirectAttributes.addAttribute("errors", errorMessages);
            return "registration";
        }
        User userFromDb = userService.findByUsername(user.getUsername());
        if (userFromDb != null) {
            redirectAttributes.addAttribute("error", true);
            return "registration";
        }
        user.setEnabled(true);
        userService.saveUser(user);
        mailSender.send(user.getEmail(), "Registration on task tracker website", "Dear, " + user.getUsername() + " thank you for registration on out task tracker website!\nIf it's not you, write to trackeralexkorep@yandex.ru.");
        return "redirect:/tracker";
    }

}
