package com.csu.junit.parameterized.spring;

import com.csu.domain.Person;
import com.csu.domain.UserController;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/spring-context-parameterized.xml")
public class Parameterized_Spring_Test extends Parameterized_Spring_Base_2<Object> {

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public int age;

    @Resource
    protected UserController userController;

    @Override
    protected Object process() {
        Person person = Person.builder()
                .name(name).age(age)
                .build();
        this.userController.addPerson(person);

        return null;
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

        List<Object[]> list = new LinkedList<>();
        list.add(new Object[]{"小明", 12});
        list.add(new Object[]{"小王", 13});
        list.add(new Object[]{"小飞", 14});

        return list;
    }
}
