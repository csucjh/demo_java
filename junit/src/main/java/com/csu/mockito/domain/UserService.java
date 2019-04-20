package com.csu.mockito.domain;

import javax.annotation.Resource;

public class UserService {

    @Resource
    private UserDao userDao;

    public Person getPerson(Person person) {
        System.out.println("UserService getPerson");
        return this.userDao.getPerson(person);
    }

    public int getAge(Person person) {
        System.out.println("UserService getAge");
        return this.userDao.getAge(person);
    }
}
