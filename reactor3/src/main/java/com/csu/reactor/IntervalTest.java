package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;

public class IntervalTest {

    @Test
    public void fluxIntervalTest() throws InterruptedException {
        Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).subscribe(System.out::println);

        //防止程序过早退出，放一个CountDownLatch拦住
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }

}
