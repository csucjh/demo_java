package com.csu.mockito;

import com.csu.domain.Person;
import com.csu.domain.UserDao;
import com.csu.domain.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/spring-context-mockito.xml")
public class SpringMockitoRuleTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    @Resource
    private UserService userService;

    // 注入spring容器中有的bean实例，并进行mock
    @Spy
    @Resource
    private UserDao spyUserDao;

    /**
     * @Spy 注解的mock对象除了被mock的方法，其余的方法都是真实返回值
     */
    @Test
    public void mockitoRule() {

        Person person = Person.builder().age(20).name("小明").feature("微胖").build();

        Mockito.doReturn(person).when(spyUserDao).getPerson(person);

        Person mock = this.userService.getPerson(person);
        Integer age = this.userService.getAge(person);

        System.out.println(mock + ":" + age);
    }
}
