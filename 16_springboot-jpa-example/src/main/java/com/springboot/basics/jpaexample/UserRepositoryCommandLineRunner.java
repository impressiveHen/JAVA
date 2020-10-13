package com.springboot.basics.jpaexample;

import com.springboot.basics.jpaexample.entity.User;
import com.springboot.basics.jpaexample.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Order(value=2)
@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory
            .getLogger(UserRepositoryCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User cindy =  new User("Cindy", "Company");
        userRepository.save(cindy);
        log.info("New User is created: " + cindy);

        Optional<User> user1 = userRepository.findById(1L);
        log.info("User1 is retrieved: " + user1);

        User shawn = new User("Shawn", "Company");
        userRepository.save(shawn);

        List<User> users = userRepository.findAll();
        log.info("All Users:" + users);

        User nameShawn = userRepository.findByName("Shawn");
        log.info("User find by name Shawn:" + nameShawn);
    }
}
