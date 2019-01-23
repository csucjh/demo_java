package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BlockTest {

    @Test
    public void block() {
        String val = Mono.just("hehe").block(Duration.of(10, ChronoUnit.SECONDS));
        System.out.println(val);
    }

}
