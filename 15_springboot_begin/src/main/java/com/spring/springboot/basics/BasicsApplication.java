package com.spring.springboot.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BasicsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BasicsApplication.class, args);

		for (String name : context.getBeanDefinitionNames()) {
			System.out.println(name);
		}
	}

}
