package com.csu.mockito.domain;

public class UserDao {

    public Person getPerson(Person person) {
        System.out.println("UserDao getPerson");
        return Person.builder()
                .age(11)
                .name("测试")
                .feature("欢快型")
                .build();
    }

    public int getAge(Person person) {
        System.out.println("UserDao getAge");
        return person.getAge() + 10;
    }
}
