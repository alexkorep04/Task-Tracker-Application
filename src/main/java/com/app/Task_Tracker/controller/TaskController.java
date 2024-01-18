package com.app.Task_Tracker.controller;

import com.app.Task_Tracker.entity.CompletedTask;
import com.app.Task_Tracker.entity.Task;
import com.app.Task_Tracker.entity.User;
import com.app.Task_Tracker.service.CompletedTaskService;
import com.app.Task_Tracker.service.TaskService;
import com.app.Task_Tracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/tracker")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final CompletedTaskService completedTaskService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService, CompletedTaskService completedTaskService) {
        this.taskService = taskService;
        this.userService = userService;
        this.completedTaskService = completedTaskService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);
        List<Task> allTasks = taskService.getAllTasks();
        List<Task> tasks = new ArrayList<>();
        List<Task> lateTasks = new ArrayList<>();
        for(Task task: allTasks) {
            if(task.getUser().getId() == user.getId() && task.getDeadline().isAfter(LocalDate.now())) {
                tasks.add(task);
            } else if (task.getUser().getId() == user.getId()) {
                lateTasks.add(task);
            }
        }
        tasks = tasks.stream().sorted(Comparator.comparing(Task::getDeadline)).toList();
        lateTasks = lateTasks.stream().sorted(Comparator.comparing(Task::getDeadline)).toList();
        model.addAttribute("lateTasks", lateTasks);
        model.addAttribute("tasks", tasks);
        return "dashboard";
    }

    @GetMapping("/dashboard/add")
    public String addTask( Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "add_task";
    }

    @PostMapping("/dashboard/add")
    public String redirectAfterAdding(HttpSession session, @ModelAttribute("task") Task task) {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);
        task.setUser(user);
        taskService.saveTask(task);
        return "redirect:/tracker/dashboard";
    }

    @PostMapping("/dashboard/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
        return "redirect:/tracker/dashboard";
    }

    @PostMapping("/dashboard/complete/{id}")
    public String completeTask(@PathVariable int id) {
        CompletedTask completedTask = new CompletedTask();
        Task task = taskService.getTask(id);
        completedTask.setUser(task.getUser());
        completedTask.setName(task.getName());
        completedTask.setType(task.getType());
        completedTask.setDeadline(task.getDeadline());
        completedTask.setDateOfCompliance(LocalDate.now());
        completedTaskService.saveTask(completedTask);
        taskService.deleteTask(id);
        return "redirect:/tracker/dashboard";
    }

    @GetMapping("/dashboard/completed")
    public String seeCompletedTasks(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);
        List<CompletedTask> allTasks = completedTaskService.getAllTasks();
        List<CompletedTask> tasks = new ArrayList<>();
        for (CompletedTask task: allTasks) {
            if (task.getUser().getId() == user.getId()) {
                tasks.add(task);
            }
        }
        tasks = tasks.stream().sorted(Comparator.comparing(CompletedTask::getDeadline)).toList();
        model.addAttribute("tasks", tasks);
        return "completed_tasks";
    }

    @PostMapping("/dashboard/completed/{id}")
    public String removeBackCompletedTask(@PathVariable int id) {
        CompletedTask completedTask = completedTaskService.getTask(id);
        Task task = new Task();
        task.setUser(completedTask.getUser());
        task.setDeadline(completedTask.getDeadline());
        task.setType(completedTask.getType());
        task.setName(completedTask.getName());
        taskService.saveTask(task);
        completedTaskService.deleteTask(id);
        return "redirect:/tracker/dashboard";
    }

    @GetMapping("/dashboard/update-task-name/{id}")
    public String updateTaskNameForm(@PathVariable int id, Model model) {
        Task task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "update_name_form";
    }

    @PostMapping("/dashboard/update-task-name/{id}")
    public String updateTaskName(@PathVariable int id, @ModelAttribute Task updatedTask) {
        Task task = taskService.getTask(id);
        taskService.deleteTask(id);
        task.setName(updatedTask.getName());
        taskService.saveTask(task);
        return "redirect:/tracker/dashboard";
    }

    @GetMapping("/dashboard/update-task-deadline/{id}")
    public String updateDeadlineForm(@PathVariable int id, Model model) {
        Task task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "update_deadline_form";
    }

    @PostMapping("/dashboard/update-task-deadline/{id}")
    public String updateTaskDeadline(@PathVariable int id, @ModelAttribute Task updatedTask) {
        Task task =taskService.getTask(id);
        taskService.deleteTask(id);
        task.setDeadline(updatedTask.getDeadline());
        taskService.saveTask(task);
        return "redirect:/tracker/dashboard";
    }

    @GetMapping("/dashboard/update-task-type/{id}")
    public String updateTaskTypeForm(@PathVariable int id, Model model) {
        Task task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "update_type_form";
    }

    @PostMapping("/dashboard/update-task-type/{id}")
    public String updateTaskType(@PathVariable int id, @ModelAttribute Task updatedTask) {
        Task task = taskService.getTask(id);
        taskService.deleteTask(id);
        task.setType(updatedTask.getType());
        taskService.saveTask(task);
        return "redirect:/tracker/dashboard";
    }
}
