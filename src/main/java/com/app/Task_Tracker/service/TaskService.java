package com.app.Task_Tracker.service;

import com.app.Task_Tracker.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTask(int id);

    void saveTask(Task task);

    void deleteTask(int id);

    int countTasksByUserId(int id);
}
