package com.example.aop;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
    /**
     * 日志信息
     * @return
     */
    String desc() default "";

}
