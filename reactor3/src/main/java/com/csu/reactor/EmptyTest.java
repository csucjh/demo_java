package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class EmptyTest {


    @Test
    public void fluxEmptyTest() {
        Flux.empty().subscribe(System.out::println);
    }

}
