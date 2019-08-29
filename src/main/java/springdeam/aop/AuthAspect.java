package springdeam.aop;

import clojure.lang.Obj;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;

@Aspect
@Component
public class AuthAspect {

    @Before("execution(* springdeam.aop.*Service.*(..))")
    public void before(JoinPoint joinPoint) throws AccessDeniedException {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        AuthPermission annotation = method.getAnnotation(AuthPermission.class);

        if (annotation != null) {
            int index = annotation.index();

            Object[] objects = joinPoint.getArgs();

            int userId = (Integer) objects[index];

            if (userId != 1) {
                throw  new AccessDeniedException("无权限查接口" + method.getName());
            }


        }


    }



}
