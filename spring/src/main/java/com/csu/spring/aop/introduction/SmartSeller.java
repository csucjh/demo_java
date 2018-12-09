package com.csu.spring.aop.introduction;

import org.springframework.stereotype.Component;

@Component
public class SmartSeller implements Seller {
    @Override
    public void sell(String goods) {
        System.out.println("sell：" + goods);
    }
}
