package com.csu.retrofit.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private int employeeId;

    private String employeeName;

    private transient String department;

    @Singular
    private List<String> features;

    private Employee friend;

    @Singular
    private Map<String, String> balls;
}
