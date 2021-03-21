package com.assignment.common.annotation;

import com.assignment.common.bean.AuthEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Auth {
    AuthEnum role() default AuthEnum.ANON;
}
