package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class AllAnyAsTest {

    @Test
    public void all() {
        Flux.just("csu", "cjh", "xxx").all(x -> x.equals("csu")).subscribe(System.out::println);
    }

    @Test
    public void any() {
        Flux.just("csu", "cjh", "xxx").any(x -> x.equals("csu")).subscribe(System.out::println);
    }

    @Test
    public void as() {
        Flux.just("csu", "cjh", "xxx").as(sink -> {
            sink.subscribe(x -> System.out.println(x + ":" + x.equals("csu")));
            return null;
        });
    }
}
