package com.app.Task_Tracker.repository;

import com.app.Task_Tracker.entity.CompletedTask;
import com.app.Task_Tracker.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Component
public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Integer> {
    @Query("SELECT ct FROM CompletedTask ct WHERE ct.user.id = :id " +
            "AND ct.dateOfCompliance BETWEEN :fromDate AND :toDate " +
            "AND ct.type = :type")
    List<CompletedTask> getTasksByDatesAndType(@Param("id") int id,
                                               @Param("fromDate") LocalDate fromDate,
                                               @Param("toDate") LocalDate toDate,
                                               @Param("type") Type type);

    @Query("SELECT ct FROM CompletedTask ct WHERE ct.user.id = :id " +
            "AND ct.dateOfCompliance BETWEEN :fromDate AND :toDate")
    List<CompletedTask> getTasksByDates(@Param("id") int id,
                                               @Param("fromDate") LocalDate fromDate,
                                               @Param("toDate") LocalDate toDate);
    int countCompletedTaskByUserId(int userId);
}
