package com.csu.junit.parameterized.spring;

import org.junit.Test;

public abstract class Parameterized_Spring_Base_2<T> extends Parameterized_Spring_Base_1 {

    @Test
    public void test() {

        mock();

        T result = process();

        assertResult(result);

    }

    protected void mock() {

    }

    protected abstract T process();

    protected void assertResult(T result) {

    }
}
