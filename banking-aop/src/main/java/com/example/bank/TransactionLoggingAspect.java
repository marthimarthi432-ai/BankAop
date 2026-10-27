package com.example.bank;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionLoggingAspect {
    @Before("execution(* com.example.bank.BankAccountService.deposit(..)) || execution(* com.example.bank.BankAccountService.withdraw(..))")
    public void logBeforeTransaction(JoinPoint joinPoint) {
        System.out.printf("[AOP BEFORE] Starting %s with amount %s%n",
                joinPoint.getSignature().getName(), joinPoint.getArgs()[0]);
    }

    @AfterReturning(pointcut = "execution(* com.example.bank.BankAccountService.deposit(..)) || execution(* com.example.bank.BankAccountService.withdraw(..))", returning = "result")
    public void logSuccessfulTransaction(JoinPoint joinPoint, Object result) {
        System.out.printf("[AOP AFTER RETURNING] %s completed successfully. New balance: %s%n",
                joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.bank.BankAccountService.deposit(..)) || execution(* com.example.bank.BankAccountService.withdraw(..))", throwing = "exception")
    public void logFailedTransaction(JoinPoint joinPoint, Throwable exception) {
        System.out.printf("[AOP AFTER THROWING] %s failed: %s%n",
                joinPoint.getSignature().getName(), exception.getMessage());
    }
}
