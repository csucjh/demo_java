package com.csu.reactor.extension;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DivisionTest {

    @Test
    public void divisionTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        Mono.just("val-one").publishOn(Schedulers.elastic())
                .subscribe(sink -> {
                    System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName() + ":" + sink);
                    latch.countDown();
                });

        Mono.just("val-two").publishOn(Schedulers.newParallel("fix", 4, true))
                .subscribe(sink -> {
                    System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName() + ":" + sink);
                    latch.countDown();
                });

        latch.await(10, TimeUnit.SECONDS);
        System.out.println("Done");
    }
}
