package com.csu.spring.lookup;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class LookupMethodServiceImpl {

    @Resource
    private CommandManager commandManager;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.commandManager.process(this));
        }
    }
}
