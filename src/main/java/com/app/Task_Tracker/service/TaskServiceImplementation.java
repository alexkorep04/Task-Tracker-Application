package com.app.Task_Tracker.service;

import com.app.Task_Tracker.entity.Task;
import com.app.Task_Tracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    @Override

    public Task getTask(int id) {
        return taskRepository.findById(id).orElseGet(null);
    }
    @Override

    public void saveTask(Task task) {
        taskRepository.save(task);
    }
    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public int countTasksByUserId(int id) {
        return taskRepository.countTasksByUserId(id);
    }
}
