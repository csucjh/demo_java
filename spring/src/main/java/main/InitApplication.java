package main;

import com.csu.spring.init.BeanInitService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitApplication {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:init-application.xml");

        System.out.println("context 启动成功");

        BeanInitService beanInitService = context.getBean(BeanInitService.class);
        // 这句将输出: hello world
        System.out.println(beanInitService.getThis());
    }
}
