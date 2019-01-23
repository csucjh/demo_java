package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Random;

public class CreateTest {

    @Test
    public void fluxCreateTest() {
        Flux.create(i -> {
            i.next("A");
            i.next("B");
            i.complete();
        }).subscribe(System.out::println);

        final Random rnd = new Random();
        Flux.create(item -> {
            for (int i = 0; i < 10; i++) {
                item.next(i);
            }
        }).subscribe(System.out::println);
    }
}
