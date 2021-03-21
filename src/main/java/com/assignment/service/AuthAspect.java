package com.assignment.service;

import com.assignment.annotation.Auth;
import com.assignment.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Autowired
    AuthService authService;

    @Pointcut(value = "@annotation(com.assignment.annotation.Auth)")
    public void pointcut() {}

    @Around("pointcut() && @annotation(auth)")
    public  Object interceptor(ProceedingJoinPoint proceedingJoinPoint, Auth auth) throws Throwable {

        if (authService.authentication("").equals(auth.role())){
                return proceedingJoinPoint.proceed();
        } else {
            log.error("Not Authorized");
            throw new AuthException();
        }
    }
}
