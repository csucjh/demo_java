package com.csu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 下面匹配中，通常 "." 代表一个包名，".." 代表包及其子包，方法参数任意匹配使用两个点 ".."
 */
@Component
@Aspect
@Order(1)
public class ThirdAspect {

    /**
     * @param jp
     */
    @Before("execution(* com.csu.spring.impl..*.*(..))")
    public void execution(JoinPoint jp) {
        System.out.println("aop impl before execution");
    }

}
