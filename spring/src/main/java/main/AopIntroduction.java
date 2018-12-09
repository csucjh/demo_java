package main;

import com.csu.spring.aop.introduction.Seller;
import com.csu.spring.aop.introduction.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Aop引介增强
 */
public class AopIntroduction {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop-introdution.xml");

        Waiter waiter = context.getBean(Waiter.class);
        waiter.greetTo("客户A");
        waiter.serviceTo("客户B");

        Seller seller = (Seller) waiter;
        seller.sell("卖电脑");
    }
}
