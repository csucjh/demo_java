package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class RangeTest {

    @Test
    public void rangeTest() {
        Flux.range(0, 100).subscribe(System.out::println);
    }

}
