package com.graduation.education.frame.intercept.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 15:38
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    boolean required() default false;

}
