package com.csu.spring.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 引介增强，相当于IntroductionInterceptor，DeclareParents注解类拥有2个成员：
 *  value：该成员用于定义切点，它表示在哪个目标类上添加引介增强；
 *  defaultImpl：默认的接口实现类。
 */
@Aspect
@Component
public class EnableSellerAspect {

    /**
     * 假设我们希望NaiveWaiter能够同时充当售货员的角色，即通过切面技术为NaiveWaiter新增Seller接口的实现。我们可以使用@AspectJ的引介增强来实现这一功能
     * 我们通过@DeclareParents为NaiveWaiter添加了一个需要实现的Seller接口，并指定其默认实现类为SmartSeller，然后通过切面技术将SmartSeller融合到NaiveWaiter中，这样NaiveWaiter就实现Seller接口
     * <p>
     * value：为NaiveWaiter添加接口实现(value只能是类)
     * defaultImpl：默认的接口实现类
     * 要实现的目标接口
     */
    @DeclareParents(value = "com.csu.spring.aop.introduction.NaiveWaiter", defaultImpl = SmartSeller.class)
    public Seller seller;
}
