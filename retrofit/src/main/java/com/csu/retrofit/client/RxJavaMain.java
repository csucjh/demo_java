package com.csu.retrofit.client;

import com.alibaba.fastjson.JSON;
import com.csu.retrofit.client.convert.FastJsonConverterFactory;
import com.csu.retrofit.model.Employee;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

public class RxJavaMain {

    public static void main(String[] args) throws Exception {
        getEmployees();
//        getEmployee();
    }


    public static void getEmployees() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        EmployeeService employeeService = retrofit.create(EmployeeService.class);

        Observable<List<Employee>> observable = employeeService.getRxEmployees();
        observable
                .observeOn(Schedulers.newThread())
                .blockingSubscribe(employee -> System.out.println(Thread.currentThread().getId() + ":" + JSON.toJSONString(employee)));
    }

    public static void getEmployee() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        EmployeeService employeeService = retrofit.create(EmployeeService.class);

        Observable<Employee> observable = employeeService.getRxEmployee();
        observable
                .observeOn(Schedulers.newThread())
                .blockingSubscribe(employee -> System.out.println(Thread.currentThread().getId() + ":" + JSON.toJSONString(employee)));

        System.out.println(Thread.currentThread().getId() + ":main end");
    }


}
