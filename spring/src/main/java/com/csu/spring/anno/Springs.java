package com.csu.spring.anno;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.*;

public class Springs {
    private List<Class<?>> classes;
    private Map<String, Object> injectedObjs;

    public static Springs newInstance() {
        return new Springs();
    }

    private Springs() {
        classes = new ArrayList<>();
        injectedObjs = new HashMap<>();
    }

    public Springs configClass(Class<?> clz) {
        classes.add(clz);
        return this;
    }

    public Springs inject(String name, Object object) {
        injectedObjs.put(name, object);
        return this;
    }

    public void create() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        ctx.setServletContext(null);
        ctx.setServletConfig(null);

        ctx.getEnvironment()
                .getPropertySources()
                .addFirst(new PropertiesPropertySource("properties", new Properties()));

        // 容器中注册配置类
        if (!CollectionUtils.isEmpty(this.classes)) {
            this.classes.forEach(ctx::register);
        }

        // 使用BeanFactoryPostProcessor向容器注入单例对象
        ctx.addBeanFactoryPostProcessor(beanFactory -> {
            this.injectedObjs.forEach(beanFactory::registerSingleton);

            // 使用BeanPostProcessor处理Bean
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                    return bean;
                }

                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    return bean;
                }
            });
        });


        // 刷新容器
        ctx.refresh();
    }
}
