package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.entity.CompletedTask;
import com.app.Task_Tracker.entity.DateAndType;
import com.app.Task_Tracker.entity.DateFilter;
import com.app.Task_Tracker.entity.Type;
import com.app.Task_Tracker.service.CompletedTaskService;
import com.app.Task_Tracker.service.TaskService;
import com.app.Task_Tracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tracker")
public class StatisticsController {
    private final UserService userService;
    private final CompletedTaskService completedTaskService;

    @Autowired
    public StatisticsController(UserService userService, CompletedTaskService completedTaskService) {
        this.userService = userService;
        this.completedTaskService = completedTaskService;
    }

    private LocalDate from;
    private LocalDate to;
    private Type type;
    @GetMapping("/dashboard/statistics-form")
    public String getDateForm(Model model,HttpSession session) {
        DateFilter dateFilter = new DateFilter();
        DateAndType dateAndType = new DateAndType(dateFilter, type);
        model.addAttribute("dateAndType", dateAndType);
        return "stat-form";
    }

    @PostMapping("/dashboard/statistics-form")
    public String redirectToStat(@ModelAttribute DateAndType dates) {
        from = LocalDate.of(2000, 1, 1);
        to = LocalDate.of(2025, 1, 1);
        type = dates.getType();
        if(dates.getDateFilter().getFrom() != null) {
            from = dates.getDateFilter().getFrom();
        }
        if(dates.getDateFilter().getTo() != null) {
            to = dates.getDateFilter().getTo();
        }
        if (to.isBefore(from)) {
           return "redirect:/tracker/dashboard/stat-form";
        }
        return "redirect:/tracker/dashboard/statistics";
    }

    @GetMapping("/dashboard/statistics")
    public String getStatistics(HttpSession session, Model model) {
        int id = userService.findByUsername(session.getAttribute("username").toString()).getId();
        List<CompletedTask> tasks;
        if (type == Type.ALL) {
            tasks = completedTaskService.getTasksByDates(id, from, to);
        } else {
            tasks = completedTaskService.getTasksByDatesAndType(id, from, to, type);
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("typeOf", type);
        return "statistics";
    }
}
