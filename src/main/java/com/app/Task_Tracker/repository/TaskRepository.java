package com.app.Task_Tracker.repository;

import com.app.Task_Tracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    int countTasksByUserId(int userId);
}
