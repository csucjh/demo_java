package com.csu.spring.aop.introduction;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public final class NaiveWaiter implements Waiter {
    @Override
    public void serviceTo(String customer) {
        System.out.println("serviceTo：" + customer);
    }

    @Override
    @Order
    public void greetTo(String customer) {
        System.out.println("greetTo：" + customer);
    }
}
