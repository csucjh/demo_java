package com.csu.junit.runner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * JUnit4ClassRunner被@Deprecated的，被BlockJUnit4ClassRunner取代了
 * 1、方法签名必须public void，且无方法参数
 * 2、方法必须有@Test注解标记
 * 3、方法上增加@Ignore注解会被忽略执行
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class BlockJUnit4ClassRunnerTest {

    @Test
    public void method() {
        System.out.println("JUnit4");
    }

    @Test
    public void method2() {
        System.out.println("JUnit4");
    }
}
