package com.csu.hessian;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Setter
@Getter
@ToString
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private int employeeId;
    private String employeeName;
    private String department;
}
