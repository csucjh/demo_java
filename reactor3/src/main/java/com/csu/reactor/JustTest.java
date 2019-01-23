package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class JustTest {
    @Test
    public void justTest() {
        Flux.just("1", "A", 3).subscribe(System.out::println);
    }
}
