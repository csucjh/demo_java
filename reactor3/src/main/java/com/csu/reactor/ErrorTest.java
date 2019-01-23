package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class ErrorTest {

    @Test
    public void fluxErrorTest() {
        Flux.error(new Exception("a wo,something is wrong!")).subscribe(System.out::println);
    }

}

