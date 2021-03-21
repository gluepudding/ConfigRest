package com.assignment.service;

import com.assignment.common.annotation.Auth;
import com.assignment.common.exception.AuthException;
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

    @Pointcut("@annotation(auth)")
    public void checkAuth(Auth auth) {}

    @Around("checkAuth(auth)")
    public  Object interceptor(ProceedingJoinPoint proceedingJoinPoint, Auth auth) throws Throwable {

        if (authService.authentication("").equals(auth.role())){
                return proceedingJoinPoint.proceed();
        } else {
            log.error("Not Authorized");
            throw new AuthException();
        }
    }
}
