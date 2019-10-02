package com.csu;

import lombok.Synchronized;

/**
 *  private static final Object $LOCK = new Object[0];
 *  private final Object $lock = new Object[0];
 *
 * 默认情况下：
 * instance methods use $lock
 * static methods use $LOCK
 * <p>
 * 使用指定的锁对象，类似@Synchronized("readLock")，readLock必须存在
 */
public class SynchronizedExample {
    private final Object readLock = new Object();

    @Synchronized
    public static void hello() {
        System.out.println("world");
    }

    @Synchronized
    public int answerToLife() {
        return 42;
    }

    /**
     *
     */
    @Synchronized("readLock")
    public void foo() {
        System.out.println("bar");
    }
}
