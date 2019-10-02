package com.csu.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = "classpath:META-INF/spring/springboot-test.xml")
public class SpringConfig {
}
