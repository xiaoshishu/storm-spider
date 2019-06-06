package com.example.annotation.annotation1;

import java.lang.annotation.*;

/**
 * 练习自定义注解
 */

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {

    String value() default "";
}
