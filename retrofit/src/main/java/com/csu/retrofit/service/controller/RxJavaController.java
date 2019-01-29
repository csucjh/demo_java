package com.csu.retrofit.service.controller;

import com.csu.retrofit.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/rx")
public class RxJavaController {

    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> getEmployees() throws IOException {

        List<Employee> employees = new LinkedList<>();

        Employee employee = Employee.builder()
                .employeeId(1)
                .employeeName("赵新国")
                .department("软件工程师")
                .ball("one", "羽毛球")
                .feature("腼腆")
                .friend(Employee.builder().employeeName("好朋友").build())
                .build();
        employees.add(employee);

        employee = Employee.builder()
                .employeeId(1)
                .employeeName("赵新国")
                .department("软件工程师")
                .ball("one", "羽毛球")
                .feature("腼腆")
                .friend(Employee.builder().employeeName("好朋友").build())
                .build();
        employees.add(employee);

        return employees;
    }

    @GetMapping("/employee")
    @ResponseBody
    public Employee getEmployee() throws IOException {

        Employee employee = Employee.builder()
                .employeeId(1)
                .employeeName("赵新国")
                .department("软件工程师")
                .ball("one", "羽毛球")
                .feature("腼腆")
                .friend(Employee.builder().employeeName("好朋友").build())
                .build();

        return employee;
    }
}
