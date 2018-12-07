import com.csu.spring.MessageService;
import com.csu.spring.lookup.CommandManager;
import com.csu.spring.replaced.MyValueCalculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);
        // 这句将输出: hello world
        System.out.println(messageService.getMessage());

        // lookup，abstract的类的bean是cglib生成的子类
        CommandManager commandManager = (CommandManager) context.getBean("commandManager");
        System.out.println(commandManager);

        // replaced
        MyValueCalculator myValueCalculator = (MyValueCalculator) context.getBean("myValueCalculator");
        System.out.println(myValueCalculator.computeValue("cal"));
        System.out.println(myValueCalculator.computeValue("cal", 123L));
        System.out.println(myValueCalculator.computeValue(123L, "cal"));
    }
}
