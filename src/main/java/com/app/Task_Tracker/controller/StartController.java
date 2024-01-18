package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.service.CompletedTaskService;
import com.app.Task_Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tracker")
public class StartController {
    private final UserService userService;
    private final CompletedTaskService completedTaskService;
    @Autowired

    public StartController(UserService userService, CompletedTaskService completedTaskService) {
        this.userService = userService;
        this.completedTaskService = completedTaskService;
    }

    @GetMapping()
    public String getStartPage() {
        return "start";
    }

    @GetMapping("/about")
    public String getAboutWebsitePage(Model model) {
        model.addAttribute("amountUsers", userService.getAllUsers().size() - 1);
        model.addAttribute("amountTasks", completedTaskService.getAllTasks().size());
        return "about";
    }
}
