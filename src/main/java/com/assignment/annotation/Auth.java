package com.assignment.annotation;

import com.assignment.utils.AuthEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Auth {
    AuthEnum role() default AuthEnum.ANON;
}
