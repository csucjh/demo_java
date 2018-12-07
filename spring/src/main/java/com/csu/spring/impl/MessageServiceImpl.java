package com.csu.spring.impl;

import com.csu.spring.MessageService;

public class MessageServiceImpl implements MessageService {

    @Deprecated
    public String getMessage() {
        return "hello world";
    }

}
