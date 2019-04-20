package com.csu.junit.runner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * SpringJUnit4ClassRunner扩展了BlockJUnit4ClassRunner
 */

// 运行于spring环境
@RunWith(SpringJUnit4ClassRunner.class)

// spring上下文配置
//@ContextConfiguration(classes = SpringConfig.class)
@ContextHierarchy(value = {@ContextConfiguration(classes = SpringConfig.class)})

// web应用注解
//@WebAppConfiguration

// 配置处理
//@ActiveProfiles("all")
//@TestPropertySource({"classpath:app-one.properties","classpath:app-two.properties"})

// 执行器监听器
//@TestExecutionListeners
public class SpringJUnit4ClassRunnerTest {

    @Value("${com.one.app}")
    private String one;

    @Value("${com.two.app}")
    private String two;

    @Test
    public void method() {
        System.out.println("SpringJUnit4");
    }
}
