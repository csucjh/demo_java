package main;

import com.csu.spring.lookup.CommandManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LookupApplication {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:lookup-application.xml");

        // lookup，abstract的类的bean是cglib生成的子类
        CommandManager commandManager = (CommandManager) context.getBean("commandManager");
        System.out.println(commandManager);
    }
}
