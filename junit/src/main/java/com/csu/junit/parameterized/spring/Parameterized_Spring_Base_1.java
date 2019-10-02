package com.csu.junit.parameterized.spring;

import org.junit.Before;
import org.springframework.test.context.TestContextManager;


public class Parameterized_Spring_Base_1 {

    protected TestContextManager testContextManager;

    @Before
    public void before() throws Exception {
        injectDependencies();
    }

    /**
     * new TestContextManager()会注册spring-test包中META-INF/spring.factories文件的TestExecutionListener
     * testContextManager.prepareTestInstance会依次执行TestExecutionListener
     *
     * @throws Exception
     */
    private void injectDependencies() throws Exception {
        // 注册TestExecutionListener
        this.testContextManager = new TestContextManager(this.getClass());

        // 处理测试实例的依赖关系
        this.testContextManager.prepareTestInstance(this);
    }
}
