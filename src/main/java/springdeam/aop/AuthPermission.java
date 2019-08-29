package springdeam.aop;


import org.omg.SendingContext.RunTime;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface AuthPermission {

    int index() default  0;

}
