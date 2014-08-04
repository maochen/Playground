package org.maochen;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {

    @Before("execution(* org.maochen.Account.print(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[Before]: " + joinPoint.getSignature().getName() + "()");
    }

    @After("execution(* org.maochen.Account.print(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("[After]: " + joinPoint.getSignature().getName() + "()");
    }

    @AfterReturning(pointcut = "execution(* org.maochen.Account.getName(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AfterReturning]: " + joinPoint.getSignature().getName() + "()");
        System.out.println("[AfterReturning]: returned value: " + result);
    }

    //    @AfterThrowing(pointcut = "execution(* org.maochen.Account.throwException(..))", throwing = "error")
    //    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
    //        System.out.println("[AfterThrowing] : " + joinPoint.getSignature().getName() + "()");
    //        System.out.println("Exception : " + error);
    //    }


    @Around("execution(* org.maochen.Account.print(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("[Around]: " + joinPoint.getSignature().getName() + "()");
        // System.out.println("arguments : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("[Around]: Around before is running!");
        joinPoint.proceed();
        System.out.println("[Around]: Around after is running!");

    }

}