package com.csu;

import lombok.experimental.var;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * var注解变量是非final类型
 * <p>
 * 由于需要配置lombok.var.flagUsage = ALLOW，而且目前版本还在Experimental包中，不建议使用
 */
public class VarExample {

    public String example() {
        var example = new ArrayList<String>();
        example.add("Hello, World!");
        var foo = example.get(0);
        return foo.toLowerCase();
    }

    public void example2() {
        var map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (var entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
