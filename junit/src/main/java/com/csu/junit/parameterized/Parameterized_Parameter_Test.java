package com.csu.junit.parameterized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class Parameterized_Parameter_Test {

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public String age;

    @Test
    public void test() {
        System.out.println(name + ":" + age);
    }

    /**
     * name用于指定debug时，Console左侧栏中的显示名称
     * <p>
     * 一般来说选择一个唯一、业务区分度高的字段作为标识名最好
     *
     * @return
     */
    @Parameterized.Parameters(name = "{index}: {0}+{1}")
    public static Iterable parameters() {

        List<String[]> list = new LinkedList<>();
        list.add(new String[]{"小明", "12"});
        list.add(new String[]{"小王", "13"});
        list.add(new String[]{"小飞", "14"});

        return list;
    }
}
