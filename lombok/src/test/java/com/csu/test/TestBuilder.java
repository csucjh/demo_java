package com.csu.test;

import com.csu.BuilderExample;

public class TestBuilder {

    public static void main(String[] args) {
        BuilderExample example = BuilderExample.builder()
                .age(24).created(11111L).name("王小二").occupation("hehe")
                .build();
        System.out.println(example.toString());
    }
}