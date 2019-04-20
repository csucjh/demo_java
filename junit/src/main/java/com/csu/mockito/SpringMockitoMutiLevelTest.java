package com.csu.mockito;

import com.csu.mockito.domain.Person;
import com.csu.mockito.domain.UserController;
import com.csu.mockito.domain.UserDao;
import com.csu.mockito.domain.UserService;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/spring-context-mockito.xml")
public class SpringMockitoMutiLevelTest {

    @InjectMocks
    @Resource
    private UserController userController;

    // 给UserController内部直接注入UserService的Proxy
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
     * 跨多层mock，最底层的使用@Spy注解或@Mock注解
     * <p>
     * 中间层有代理类，也需要解决不生效问题
     */
    @Test
    public void mockitoMutiLevel() {
        Person person = Person.builder()
                .age(20)
                .name("小明")
                .feature("微胖")
                .build();

        // Mockito.any()表示对任何入参都返回mock值
        doReturn(person).when(spyUserDao).getPerson(any());

        // 由于userService是代理类，需要获取真实的target然后将其userDao字段设置为mock对象spyUserDao
        ReflectionTestUtils.setField(AopProxyUtils.getSingletonTarget(userService), "userDao", spyUserDao);

        Person level = this.userController.getPerson(person);
        int age = this.userController.getAge(person);

        System.out.println(level + ":" + age);
    }
}
