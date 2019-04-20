package com.csu.junit.runner;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;

/**
 * 兼容junit3的调用
 * 1、必须继承TestCase
 * 2、方法名称必须以test开头
 * 3、方法签名必须public void，且无方法参数
 */
@RunWith(JUnit38ClassRunner.class)
public class JUnit38ClassRunnerTest extends TestCase {

    @Test
    public void testMethod() {
        System.out.println("JUnit38");
    }
}
