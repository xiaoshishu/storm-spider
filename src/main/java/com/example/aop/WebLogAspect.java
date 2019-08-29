package com.example.aop;

import com.google.gson.Gson;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect

public class WebLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 定义一个切点
     */
    @Pointcut("@annotation(com.example.aop.WebLog)")
    public void webLog(){}

    @Around("webLog()")
    public  void doAroud(ProceedingJoinPoint proceedingJoinPoint){
        try{
            long startTime = System.currentTimeMillis();
            Object result = proceedingJoinPoint.proceed();
            // 打印出参
            logger.info("Response Args : {}" , new Gson().toJson(result));
            // 执行耗时
            logger.info("Time-Consuming: {} ms", System.currentTimeMillis() - startTime);
        } catch (Throwable throwable){
            throwable.printStackTrace();
            System.out.println("发生异常" + throwable.toString());
        }
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录一下请求内容
        logger.info("URL : {}", request.getRequestURI());
        logger.info("HTTP_METHOD : {}", request.getMethod());
        logger.info("IP : {}", request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "webLog()", returning = "object")
    public void doAfter(Object object){
        logger.info("RESPONSE" + object);
    }



}
