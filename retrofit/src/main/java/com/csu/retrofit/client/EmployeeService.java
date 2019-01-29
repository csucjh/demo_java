package com.csu.retrofit.client;

import com.csu.retrofit.model.Employee;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface EmployeeService {


    @GET("index/employees")
    Call<List<Employee>> getEmployees();


    @GET("index/employee")
    Call<Employee> getEmployee();
}
