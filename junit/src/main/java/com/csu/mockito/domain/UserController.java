package com.csu.mockito.domain;

import javax.annotation.Resource;

public class UserController {

    @Resource
    // @Resource(name = "userServiceProxy")
    private UserService userService;


    public Person getPerson(Person person) {
        System.out.println("UserController getPerson");
        return this.userService.getPerson(person);
    }

    public int getAge(Person person) {
        System.out.println("UserController getAge");
        return this.userService.getAge(person);
    }
}
