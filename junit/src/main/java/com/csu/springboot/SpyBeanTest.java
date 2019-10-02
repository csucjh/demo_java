package com.csu.springboot;

import com.csu.domain.Person;
import com.csu.domain.UserDao;
import com.csu.domain.UserService;
import com.csu.springboot.config.SpringConfig;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringConfig.class)
public class SpyBeanTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    /**
     * 不在需要使用@InjectMocks来注入mock对象实例
     * <p>
     * 不存在代理类问题
     */
    @Resource(name = "userServiceProxy")
    private UserService userService;

    @SpyBean
    private UserDao userDao;

    @Test
    public void testSpyBean() {
        Person person = Person.builder().age(20).name("小明").feature("微胖").build();

        doReturn(person).when(userDao).getPerson(any());

        Person mock = this.userService.getPerson(person);
        int age = this.userService.getAge(person);

        System.out.println(mock + ":" + age);
    }
}
