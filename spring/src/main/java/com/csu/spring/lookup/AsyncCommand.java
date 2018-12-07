package com.csu.spring.lookup;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("asyncCommand")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AsyncCommand implements Command {

    private Object state;

    @Override
    public void setState(Object obj) {
        this.state = obj;
    }

    @Override
    public Object execute() {
        return this.toString() + "-" + state.toString();
    }
}
