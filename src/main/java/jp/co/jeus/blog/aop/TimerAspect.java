package jp.co.jeus.blog.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Log4j2
@Aspect
@Component
public class TimerAspect {

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Timer {
    }

    @Around("@annotation(jp.co.jeus.blog.aop.TimerAspect.Timer)")
    public Object timerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String targetName = joinPoint.getSignature().getDeclaringTypeName();
        targetName = targetName.substring(targetName.lastIndexOf(".")) + "." + joinPoint.getSignature().getName();
        Object res = joinPoint.proceed();
        log.info(targetName + " " + (System.currentTimeMillis() - start) + "ms");
        return res;
    }
}
