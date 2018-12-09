package main;

import com.csu.spring.replaced.MyValueCalculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReplacedApplication {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:replaced-application.xml");

        // replaced
        MyValueCalculator myValueCalculator = (MyValueCalculator) context.getBean("myValueCalculator");
        System.out.println(myValueCalculator.computeValue("cal"));
        System.out.println(myValueCalculator.computeValue("cal", 123L));
        System.out.println(myValueCalculator.computeValue(123L, "cal"));
    }
}
