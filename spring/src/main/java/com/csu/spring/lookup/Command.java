package com.csu.spring.lookup;

public interface Command {

    void setState(Object obj);

    Object execute();
}
