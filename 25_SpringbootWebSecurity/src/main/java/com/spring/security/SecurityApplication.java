package com.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityApplication {
	private static final Logger logger = LoggerFactory.getLogger(SecurityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
		logger.debug(new BCryptPasswordEncoder().encode("123"));
	}

}
