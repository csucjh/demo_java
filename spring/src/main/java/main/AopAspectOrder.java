package main;

import com.csu.spring.aop.introduction.Seller;
import com.csu.spring.aop.introduction.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAspectOrder {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop-order.xml");

        Waiter waiter = context.getBean(Waiter.class);
        waiter.greetTo("客户A");
        System.out.println("----------------------");
        waiter.serviceTo("客户B");
        System.out.println("----------------------");

        Seller seller = (Seller) waiter;
        seller.sell("卖电脑");
        System.out.println("----------------------");
    }
}
