package com.csu.concurrency.threadpool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ScheduledThreadPoolExecutor service = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
        Random random = new Random();

        for (int i = 0; i < 100 * 1000; i++) {
            service.schedule(() -> {
                System.out.println(Thread.currentThread().getId());
            }, random.nextInt(10), TimeUnit.SECONDS);
            System.out.println("队列长度:" + service.getQueue().size());
        }

        Thread.sleep(5 * 60 * 1000);
    }
}
