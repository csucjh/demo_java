package com.csu.reactor.subscriber;

import reactor.core.publisher.Flux;

public class TestSubscriber {

    public static void main(String[] args) {
        SampleSubscriber<Integer> subscriber = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
//        ints.subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error " + error),
//                () -> {
//                    System.out.println("Done");
//                },
//                s -> subscriber.request(10));
        ints.subscribe(subscriber);
    }
}
