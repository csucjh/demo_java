package com.csu;

import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;

public class ValueExample {
    String name;

    @NonFinal
    int age;

    double score;

    protected String[] tags;

    @ToString
    @Value(staticConstructor = "of")
    public static class Exercise<T> {
        String name;
        T value;
    }
}
