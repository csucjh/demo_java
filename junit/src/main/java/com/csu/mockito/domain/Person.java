package com.csu.mockito.domain;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    private int age;

    private String name;

    @Singular
    private List<String> features;

}
