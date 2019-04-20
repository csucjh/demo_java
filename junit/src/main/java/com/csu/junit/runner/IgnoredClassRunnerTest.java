package com.csu.junit.runner;

import org.junit.Test;
import org.junit.internal.builders.IgnoredClassRunner;
import org.junit.runner.RunWith;

/**
 * 这个实际意义不大，就是忽略当前测试类
 */
@RunWith(IgnoredClassRunner.class)
public class IgnoredClassRunnerTest {

    @Test
    public void test() {
        System.out.println("ignore");
    }

}
