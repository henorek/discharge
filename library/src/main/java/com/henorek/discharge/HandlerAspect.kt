package com.henorek.discharge

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect class HandlerAspect {

  @Pointcut("execution(@com.henorek.discharge.HandleException * *(..))")
  private fun methodAnnotatedWithHandleException() {}

  @Around("methodAnnotatedWithHandleException()")
  @Throws(Throwable::class)
  fun proceedToHandler(joinPoint: ProceedingJoinPoint) {
    try {
      joinPoint.proceed()
    } catch (exception: Exception) {
      val exceptionClass = exception.javaClass
      if (Discharge.isSolvable(exceptionClass)) {
        Discharge.takeSolution(exceptionClass).solve()
      } else throw RuntimeException(exception)
    }

  }
}
