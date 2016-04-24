package com.henorek.discharge;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect public class HandlerAspect {

  private final Discharge discharge = Discharge.getInstance();

  @Pointcut("execution(@com.henorek.discharge.HandleException * *(..))")
  private void methodAnnotatedWithHandleException() {
  }

  @Around("methodAnnotatedWithHandleException()")
  public void proceedToHandler(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      joinPoint.proceed();
    } catch (Exception exception) {
      Class exceptionClass = exception.getClass();
      if (discharge.isSolvable(exceptionClass)) {
        discharge.takeSolution(exceptionClass).solve();
      } else throw new RuntimeException(exception);
    }
  }
}
