package com.csu.study;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSequence {

    static ZooKeeper zkClient;

    static ExecutorService es = Executors.newFixedThreadPool(5);

    static AtomicInteger innc = new AtomicInteger(0);

    static {
        try {
//            zkClient = new ZooKeeper("10.6.1.135:2181", 3000000, null);
            zkClient = new ZooKeeper("localhost:2181", 3000000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 100; i++) {
            es.submit(() -> {
                try {
                    byte[] bytes = zkClient.getData("/app1/element0000000100", null, null);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(innc.incrementAndGet() + ":" + Thread.currentThread().getId());
            });
        }

        Thread.sleep(TimeUnit.SECONDS.toSeconds(1000));
        System.out.println("end");
    }
}
