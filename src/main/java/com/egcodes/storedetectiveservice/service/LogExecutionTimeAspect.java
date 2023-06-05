package com.egcodes.storedetectiveservice.service;

import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecutionTimeAspect {

    @Around("execution(* com.egcodes.storedetectiveservice..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        var start = Instant.now();
        var proceed = joinPoint.proceed();
        var finish = Instant.now();
        var timeElapsed = java.time.Duration.between(start, finish).toMillis();

        log.debug(joinPoint.getSignature() + " executed in " + timeElapsed + "ms");

        return proceed;
    }
}