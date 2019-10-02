package com.csu.mockito;

import com.csu.domain.Person;
import com.csu.domain.UserDao;
import com.csu.domain.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/spring-context-mockito.xml")
public class SpringMockitoMockTest {

    @InjectMocks
    @Resource
    private UserService userService;

    @Mock
    private UserDao mockUserDao;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @Mock 注解的mock对象除了被mock的方法有返回值，其余的方法均被置空（0或者null）,就是说@Mock会mock掉整个对象
     */
    @Test
    public void mockitoMock() {

        Person person = Person.builder().age(20).name("小明").feature("微胖").build();

        // 由于@Mock是mock整个userDao对象，所以都不会调用真实的userDao实例的getPerson方法，下面两句效果等价
        // any()意思是调用getPerson时不管入参是多少都要返回mock值
        doReturn(person).when(mockUserDao).getPerson(any());
        when(mockUserDao.getPerson(any())).thenReturn(person);

        Person mock = this.userService.getPerson(person);
        int age = this.userService.getAge(person);

        System.out.println(mock + ":" + age);
    }

}
