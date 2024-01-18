package com.app.Task_Tracker.service;

import com.app.Task_Tracker.entity.CompletedTask;
import com.app.Task_Tracker.entity.Type;
import com.app.Task_Tracker.repository.CompletedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompletedTaskServiceImplementation implements CompletedTaskService {
    @Autowired
    private CompletedTaskRepository completedTaskRepository;
    @Override
    public List<CompletedTask> getAllTasks() {
        return completedTaskRepository.findAll();
    }

    @Override
    public CompletedTask getTask(int id) {
        return completedTaskRepository.findById(id).orElseGet(null);
    }

    @Override
    public void saveTask(CompletedTask task) {
        completedTaskRepository.save(task);
    }

    @Override
    public void deleteTask(int id) {
        completedTaskRepository.deleteById(id);
    }

    @Override
    public List<CompletedTask>getTasksByDatesAndType(int id, LocalDate from, LocalDate to, Type type) {
        return completedTaskRepository.getTasksByDatesAndType(id, from, to, type);
    }

    @Override
    public List<CompletedTask> getTasksByDates(int id, LocalDate from, LocalDate to) {
        return completedTaskRepository.getTasksByDates(id, from, to);
    }

    @Override
    public int countCompletedTaskByUserId(int id) {
        return completedTaskRepository.countCompletedTaskByUserId(id);
    }
}
