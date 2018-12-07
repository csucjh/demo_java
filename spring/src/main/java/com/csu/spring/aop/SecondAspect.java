package com.csu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 下面匹配中，通常 "." 代表一个包名，".." 代表包及其子包，方法参数任意匹配使用两个点 ".."
 */
@Component
@Aspect
@Order(2)
public class SecondAspect {

    /**
     * within：指定所在类或所在包下面的方法（Spring AOP 独有）
     */
    @Before("within(com.csu.spring.impl..*)")
    public void within(JoinPoint jp) {
        System.out.println("aop impl before within");
    }

}
