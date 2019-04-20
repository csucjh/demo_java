package com.csu.mockito;

import com.csu.mockito.domain.Person;
import com.csu.mockito.domain.UserDao;
import com.csu.mockito.domain.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/spring-context-mockito.xml")
public class SpringMockitoVerifyTest extends AbstractJUnit4SpringContextTests {

    @Captor
    ArgumentCaptor<Person> captor;


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
    public void mockitoVerify() {

        Person person = Person.builder().age(20).name("小明").feature("微胖").build();

        Mockito.doReturn(person).when(spyUserDao).getPerson(person);

        Person mock = this.userService.getPerson(person);
        mock = this.userService.getPerson(person);

        // 这里只能是mock的对象spyUserDao
        // 验证getPerson方法至少被调用了一次/两次
        // ArgumentCaptor可以捕获所有调用的入参参数
        Mockito.verify(spyUserDao, Mockito.atLeastOnce()).getPerson(captor.capture());
        Mockito.verify(spyUserDao, Mockito.atLeast(2)).getPerson(Mockito.any());

        System.out.println(mock);

        System.out.println(captor.getAllValues());
    }
}
