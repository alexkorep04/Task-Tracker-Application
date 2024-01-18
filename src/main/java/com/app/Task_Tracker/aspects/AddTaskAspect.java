package com.app.Task_Tracker.aspects;

import com.app.Task_Tracker.entity.Task;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AddTaskAspect {
    @Around(value =
            "execution(public String com.app.Task_Tracker.controller.TaskController.redirectAfterAdding(..)) " +
                    "&& args(session, task,..)"
            , argNames = "joinPoint,session,task")
    public Object checkAddTask(ProceedingJoinPoint joinPoint, HttpSession session, Task task) throws Throwable {
        if (task.getDeadline() == null || task.getName() == null || task.getType() == null) {
            return "add_task";
        }
        return joinPoint.proceed();
    }
}
