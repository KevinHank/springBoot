package com.kevin.demo.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  @author: Kee.Li
 *  @description: 自定义JSON过滤注解
 *  @date: 2017/9/21 13:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFilter {
    Class<?> type();
    String include() default "";
    String exclude() default "";
}