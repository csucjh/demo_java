package com.csu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareAnnotation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 下面匹配中，通常 "." 代表一个包名，".." 代表包及其子包，方法参数任意匹配使用两个点 ".."
 */
@Component
@Aspect
@Order(3)
public class FirstAspect {
    /**
     * @annotation：方法上具有特定的注解，注意是方法
     */
    @Before("execution(* com.csu.spring.impl..*.*(..)) && @annotation(java.lang.Deprecated)")
    public void executionAnno(JoinPoint jp) {
        System.out.println("aop impl before execution with annotation");
    }

    /**
     * bean(idOrNameOfBean)：匹配 bean 的名字（Spring AOP 独有）
     */
    @Around("bean(*Service)")
    public Object bean(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("aop impl around before bean");
        Object object = jp.proceed();
        System.out.println("aop impl around after bean");
        return object;
    }
}
