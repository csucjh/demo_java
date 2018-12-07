package com.csu.spring.impl;

import com.csu.spring.BeanInitService;
import com.csu.spring.MessageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Constructor >( @Autowired、@Resource、@Value 等属性注解) >@PostConstruct > InitializingBean > init-method
 */
public class BeanInitServiceImpl implements BeanInitService, InitializingBean {

    @Autowired
    private MessageService messageService;

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean");
    }

    public void initMethod() {
        System.out.println("init-method");
    }
}
