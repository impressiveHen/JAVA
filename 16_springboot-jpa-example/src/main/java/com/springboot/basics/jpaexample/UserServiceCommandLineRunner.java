package com.springboot.basics.jpaexample;

import com.springboot.basics.jpaexample.entity.User;
import com.springboot.basics.jpaexample.service.UserDAOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
CommandLineRunner is an interface used to indicate that a bean should run when it is
contained within a SpringApplication. A Spring Boot application can have multiple
beans implementing CommandLineRunner. These can be ordered with @Order.
 */
@Order(value=1)
@Component
public class UserServiceCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory
            .getLogger(UserServiceCommandLineRunner.class);

    @Autowired
    private UserDAOService userDAOService;

    /*
    This method will be executed after the application context is loaded and right
    before the Spring Application run method is completed. So you could use it to
    check if certain beans exist or what values of certain properties are.
    Another reason to use it is to load some data right before your application fires up.
     */
    @Override
    public void run(String... args) throws Exception {
        User user =  new User("Jack", "Admin");
        long insertedId = userDAOService.insert(user);
        log.info("New User is created: " + user);
    }
}
