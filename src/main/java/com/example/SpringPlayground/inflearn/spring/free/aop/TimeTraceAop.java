package com.example.SpringPlayground.inflearn.spring.free.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    /**
     * 시간 뿐만 아닌 공통적으로 해야할일들을 처리 가능함
     */
    @Around("execution(* com.example.SpringPlayground..*(..))")
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + jointPoint.toShortString());
        try {
            return  jointPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + jointPoint.toShortString() + " " + timeMs+ "ms");
        }
    }
}
