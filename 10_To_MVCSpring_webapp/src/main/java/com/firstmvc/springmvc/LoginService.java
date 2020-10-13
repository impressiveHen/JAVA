package com.firstmvc.springmvc;

import org.springframework.stereotype.Service;

// define bean
@Service
public class LoginService {
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("Shiang") && password.equals("123");
	}

}