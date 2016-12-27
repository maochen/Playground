package org.maochen.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectjInject {


  @Before("execution(* org.maochen.aspectj.Model.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    System.out.println("[Before]: " + joinPoint.getSignature().getName() + "()");
  }

  @After("execution(* org.maochen.aspectj.Model.example(..))")
  public void logAfter(JoinPoint joinPoint) {
    System.out.println("[After]: " + joinPoint.getSignature().getName() + "()");
  }

  @AfterReturning(pointcut = "execution(* org.maochen.aspectj.Model.*(..))", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("[AfterReturning]: " + joinPoint.getSignature().getName() + "()" + "\treturned value: " + result);
  }

  @AfterThrowing(pointcut = "execution(* org.maochen.aspectj.Model.throwException(..))", throwing = "error")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
    System.out.println("[AfterThrowing] : " + joinPoint.getSignature().getName() + "()");
    System.out.println("Exception : " + error);
  }


  /**
   * around example.
   *
   * @param joinPoint join point.
   * @throws Throwable exception itself.
   */
  @Around("execution(* org.maochen.aspectj.Model.example(..))")
  public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.print("[Around]: " + joinPoint.getSignature().getName() + "()");
    System.out.println(" Around before is running!");
    joinPoint.proceed();
    System.out.println("[Around]: Around after is running!");
  }
}
