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
//@Order(2)
@Priority(2)
public class SecondAspect {

    /**
     * within：指定所在类或所在包下面的方法（Spring AOP 独有）
     */
    @Before("within(com.csu.spring..*)")
    public void within(JoinPoint jp) {
        System.out.println("second: aop impl before within");
    }

}
