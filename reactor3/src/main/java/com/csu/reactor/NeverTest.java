package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class NeverTest {


    @Test
    public void fluxNeverTest() {
        Flux.never().subscribe(System.out::println);
    }

}
