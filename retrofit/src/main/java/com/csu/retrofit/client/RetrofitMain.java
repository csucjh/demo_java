package com.csu.retrofit.client;

import com.alibaba.fastjson.JSON;
import com.csu.retrofit.client.convert.FastJsonConverterFactory;
import com.csu.retrofit.model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.List;

public class RetrofitMain {

    public static void main(String[] args) {
//        getEmployees();
        getEmployee();
    }


    public static void getEmployees() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

        EmployeeService employeeService = retrofit.create(EmployeeService.class);
        Call<List<Employee>> call = employeeService.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {

            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                //数据请求成功
                System.out.println(JSON.toJSONString(response.body()));
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                //数据请求失败
                System.out.println(t);
            }
        });
    }

    public static void getEmployee() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

        EmployeeService employeeService = retrofit.create(EmployeeService.class);
        Call<Employee> call = employeeService.getEmployee();
        call.enqueue(new Callback<Employee>() {

            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                //数据请求成功
                System.out.println(Thread.currentThread().getId() + ":" + JSON.toJSONString(response.body()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                //数据请求失败
                System.out.println(t);
            }
        });

        System.out.println(Thread.currentThread().getId() + ":main end");
    }


}
