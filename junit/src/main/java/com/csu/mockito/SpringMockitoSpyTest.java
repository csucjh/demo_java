package com.csu.mockito;

import com.csu.mockito.domain.Person;
import com.csu.mockito.domain.UserDao;
import com.csu.mockito.domain.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/spring-context-mockito.xml")
public class SpringMockitoSpyTest {

    @InjectMocks
    @Resource
    private UserService userService;

    // 注入spring容器中有的bean实例，并进行mock
    @Spy
    @Resource
    private UserDao spyUserDao;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * @Spy 注解的mock对象除了被mock的方法，其余的方法都是真实返回值
     */
    @Test
    public void mockitoSpy() {

        Person person = Person.builder().age(20).name("小明").feature("微胖").build();

        doReturn(person).when(spyUserDao).getPerson(person);

        Person mock = this.userService.getPerson(person);
        int age = this.userService.getAge(person);

        System.out.println(mock + ":" + age);
    }


}
