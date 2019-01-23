package com.csu.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FromTest {

    @Test
    public void fromIterableTest() {
        List<String> list = Arrays.asList("one", "two", "three");
        Flux.fromIterable(list).subscribe(System.out::println);
    }

    @Test
    public void fromArrayTest() {
        String[] array = Arrays.asList("one", "two", "three").toArray(new String[0]);
        Flux.fromArray(array).subscribe(System.out::println);
    }

    @Test
    public void fromStreamTest() {
        Stream<Double> stream = Stream.generate(Math::random).limit(20);
        Flux.fromStream(stream).subscribe(System.out::println);
    }


    @Test
    public void fromSupplierTest() {
        Mono.fromSupplier(() -> "supply").subscribe(System.out::println);
    }

    @Test
    public void fromCallableTest() {
        Mono.fromCallable(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "five";
        }).subscribe(System.out::println);
    }

    @Test
    public void fromRunnableTest() {
        Mono.fromRunnable(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).subscribe(System.out::println);
    }

    @Test
    public void fromFutureTest() {
        Mono.fromFuture(new CompletableFuture<String>() {
            @Override
            public String get() throws InterruptedException, ExecutionException {
                return "completableFuture";
            }
        }).subscribe(System.out::println);
    }
}
