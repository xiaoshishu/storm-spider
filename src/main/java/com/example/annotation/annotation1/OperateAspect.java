package com.example.annotation.annotation1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 联系自定义注解  切面类
 */

@Component
@Aspect
public class OperateAspect {


    @Pointcut("@annotation(com.example.annotation.annotation1.MyLog)")
    public void annotationPointCut(){

    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint){

        MethodSignature  methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        MyLog myLog = method.getAnnotation(MyLog.class);

        System.out.println("打印" + myLog.value()+ "之前");

    }

    @Around("annotationPointCut()")
    public Object advice(ProceedingJoinPoint joinPoint){
        System.out.println("通知之前");
        Object retmsg = null;
        try{
            retmsg = joinPoint.proceed();
            System.err.println("+++++++"+ retmsg);
        } catch (Throwable e){
            e.printStackTrace();
        }

        System.out.println("通知之后");
        return  retmsg;

    }

    @After("annotationPointCut()")
    public void after() {
        System.out.println("after 方法之后");
    }




}
