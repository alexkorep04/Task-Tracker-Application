package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.entity.User;
import com.app.Task_Tracker.entity.UserAndTask;
import com.app.Task_Tracker.exceptions.NoSuchUserException;
import com.app.Task_Tracker.service.CompletedTaskService;
import com.app.Task_Tracker.service.TaskService;
import com.app.Task_Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/tracker")
public class RestController {
    private final TaskService taskService;
    private final UserService userService;
    private final CompletedTaskService completedTaskService;

    @Autowired
    public RestController(TaskService taskService, UserService userService, CompletedTaskService completedTaskService) {
        this.taskService = taskService;
        this.userService = userService;
        this.completedTaskService = completedTaskService;
    }

    @GetMapping("/api/{username}")
    public UserAndTask getUser(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new NoSuchUserException("Sorry, there is no registered user with such name!");
        }
        int id = user.getId();
        int amountOfCompletedTasks = taskService.countTasksByUserId(id);
        int amountOfActiveTasks = completedTaskService.countCompletedTaskByUserId(id);
        return new UserAndTask(id, username, amountOfActiveTasks, amountOfCompletedTasks);
    }

    @GetMapping("/api")
    public List<UserAndTask> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserAndTask> userAndTasks = new ArrayList<>();
        for (User user : users) {
            userAndTasks.add(new UserAndTask(user.getId(), user.getUsername(), taskService.countTasksByUserId(user.getId()), completedTaskService.countCompletedTaskByUserId(user.getId())));
        }
        return userAndTasks;
    }
}
