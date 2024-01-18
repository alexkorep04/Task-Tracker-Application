package com.app.Task_Tracker.aspects;

import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationAspect {

    private final HttpSession httpSession;

    @Autowired
    public AuthenticationAspect(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Pointcut("execution(* com.app.Task_Tracker.controller.TaskController.*(..)))")
    private void allTaskControllerMethods() { }

    @Pointcut("execution(* com.app.Task_Tracker.controller.AdminController.*(..)))")
    private void allAdminControllerMethods() { }

    @Pointcut("execution(* com.app.Task_Tracker.controller.AccountController.*(..)))")
    private void allAccountControllerMethods() { }

    @Pointcut("execution(* com.app.Task_Tracker.controller.StatisticsController.*(..)))")
    private void allStatisticsControllerMethods() { }

    @Around("allTaskControllerMethods() || allAccountControllerMethods() || allAdminControllerMethods() || allStatisticsControllerMethods()")
    public Object checkAuthentication(ProceedingJoinPoint joinPoint) throws Throwable {
        String username = (String) httpSession.getAttribute("username");
        String password = (String) httpSession.getAttribute("password");
        if (username == null || password == null) {
            return "redirect:/tracker/login";
        }
        return joinPoint.proceed();
    }
}
