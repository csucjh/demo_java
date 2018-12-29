package com.csu.concurrency.thread;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws Exception {
//        test1();
        test2();
    }


    /**
     * 分析：
     * child线程：
     * 1、拿到了锁并且没有释放：locked <0x00000007d5df43f8> (a java.util.HashMap)
     * 2、线程调用了sleep方法，java.lang.Thread.State: TIMED_WAITING (sleeping)
     * <p>
     * main线程：
     * 1、java.lang.Thread.State: BLOCKED (on object monitor)：因为锁被Thread-0 占用，拿不到锁( waiting for monitor entry)，进入 BLOCKED 状态
     * 2、waiting to lock <0x00000007d5df43f8> (a java.util.HashMap)：首先锁的编号是：0x00000007d5df43f8，锁的对象是HashMap (a java.util.HashMap)
     * <p>
     * 总结：
     * 1、这种方式可以轻松分析出来 BLOCKED 的线程是被那个锁阻塞的
     * 2、从代码的输出可以看出：线程Thread-0 持有锁，进入sleep后，不会释放锁，否则main线程的（##############） 会输出
     *
     * @throws Exception
     */
    private static void test1() throws Exception {
        final Map<String, User> map = new HashMap<>();
        new Thread("child") {
            @Override
            public void run() {
                synchronized (map) {
                    try {
                        System.out.println("***************");

                        System.out.println("map: " + Long.toHexString(map.hashCode()));
                        System.out.println("map: " + (map));
                        System.out.println("newThread: " + (Thread.currentThread().getId()));
                        System.out.println("newThread: " + Long.toHexString(Thread.currentThread().getId()));
                        System.out.println("newThread: " + Long.toHexString(Thread.currentThread().hashCode()));

                        Thread.sleep(10000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Thread.sleep(10000);
        System.out.println("mainThread: " + (Thread.currentThread().getId()));
        System.out.println("mainThread: " + Long.toHexString(Thread.currentThread().getId()));
        System.out.println("mainThread: " + Long.toHexString(Thread.currentThread().hashCode()));
        synchronized (map) {
            System.out.println("##############");
            map.wait();
        }

        int i = 0;
        while (true) {
            map.put("value" + i, new User(i));
            i++;
            if (map.size() > 1000000) {
                map.clear();
            }
        }

    }


    private static void test2() throws Exception {
        final Map<String, User> map = new HashMap<>();
        new Thread("child") {
            @Override
            public void run() {
                synchronized (map) {
                    try {
                        System.out.println("***************");
                        System.out.println("newThread: " + Long.toHexString(Thread.currentThread().getId()));
                        System.out.println("newThread: " + Long.toHexString(Thread.currentThread().hashCode()));

                        map.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Thread.sleep(10000);
        System.out.println("mainThread: " + Long.toHexString(Thread.currentThread().getId()));
        System.out.println("mainThread: " + Long.toHexString(Thread.currentThread().hashCode()));
        synchronized (map) {
            System.out.println("##############");
            map.wait();
        }

        int i = 0;
        while (true) {
            map.put("value" + i, new User(i));
            i++;
            if (map.size() > 1000000) {
                map.clear();
            }
        }

    }

    private static class User {
        public User(int i) {

        }
    }
}
