package com.csu.spring.aop.order;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

/**
 * 下面匹配中，通常 "." 代表一个包名，".." 代表包及其子包，方法参数任意匹配使用两个点 ".."
 */
@Component
@Aspect
//@Order(4)
@Priority(4)
public class FourthAspect {
    /**
     * bean(idOrNameOfBean)：匹配 bean 的名字（Spring AOP 独有）
     */
    @Around("bean(*Waiter)")
    public Object bean(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("aop impl around before bean");
        Object object = jp.proceed();
        System.out.println("aop impl around after bean");
        return object;
    }

}
