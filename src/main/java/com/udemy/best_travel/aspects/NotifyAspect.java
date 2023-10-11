package com.udemy.best_travel.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class NotifyAspect {

    @After(value = "@annotation(com.udemy.best_travel.util.annotations.Notify)")
    public void notifyInFile(JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
    }
}
