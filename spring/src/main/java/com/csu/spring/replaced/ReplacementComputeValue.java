package com.csu.spring.replaced;

import org.springframework.beans.factory.support.MethodReplacer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class ReplacementComputeValue implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        String input = String.valueOf(args[0]);
        if (args.length > 1) {
            input += "-" + args[1];
        }
        return input + "-replaced";
    }

}
