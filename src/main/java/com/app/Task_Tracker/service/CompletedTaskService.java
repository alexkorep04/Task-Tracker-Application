package com.app.Task_Tracker.service;

import com.app.Task_Tracker.entity.CompletedTask;
import com.app.Task_Tracker.entity.Task;
import com.app.Task_Tracker.entity.Type;

import java.time.LocalDate;
import java.util.List;

public interface CompletedTaskService {
    List<CompletedTask> getAllTasks();

    CompletedTask getTask(int id);

    void saveTask(CompletedTask task);

    void deleteTask(int id);

    List<CompletedTask> getTasksByDatesAndType(int id, LocalDate from, LocalDate to, Type type);
    List<CompletedTask> getTasksByDates(int id, LocalDate from, LocalDate to);
    int countCompletedTaskByUserId(int id);
}
