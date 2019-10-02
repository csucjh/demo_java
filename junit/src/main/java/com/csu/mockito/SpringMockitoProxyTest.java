package com.csu.mockito;

import com.csu.domain.Person;
import com.csu.domain.UserDao;
import com.csu.domain.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/spring-context-mockito.xml")
public class SpringMockitoProxyTest {

    @InjectMocks
    @Resource(name = "userServiceProxy")
    private UserService userService;

    @Spy
    @Resource
    private UserDao spyUserDao;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * UserService内部依赖UserDao，如果我想mock掉UserDao的getPerson方法
     * <p>
     * 有代理的情况下会失效
     */
    @Test
    public void mockitoProxy() {

        Person person = Person.builder().age(20).name("小明").feature("微胖").build();

        doReturn(person).when(spyUserDao).getPerson(any());

        // 由于userService是代理类，需要获取真实的target然后将其userDao字段设置为mock对象spyUserDao
        ReflectionTestUtils.setField(AopProxyUtils.getSingletonTarget(userService), "userDao", spyUserDao);

        Person proxy = this.userService.getPerson(person);
        int age = this.userService.getAge(person);

        System.out.println(proxy + ":" + age);
    }
}
