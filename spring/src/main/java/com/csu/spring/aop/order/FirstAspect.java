package com.csu.spring.aop.order;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

/**
 * 下面匹配中，通常 "." 代表一个包名，".." 代表包及其子包，方法参数任意匹配使用两个点 ".."
 */
@Component
@Aspect
//@Order(1)
@Priority(1)
public class FirstAspect {
    /**
     * @annotation：方法上具有特定的注解，注意是方法
     */
    @Before("execution(* com.csu.spring..*.*(..)) && @annotation(org.springframework.core.annotation.Order)")
    public void executionAnno(JoinPoint jp) {
        System.out.println("aop impl before execution with annotation");
    }

}
