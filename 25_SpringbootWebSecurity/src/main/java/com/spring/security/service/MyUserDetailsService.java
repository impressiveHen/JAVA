package com.spring.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    private static List<UserObject> users = new ArrayList<>();

    /*
    In a real application, instead of using local data, we will find user details
    by some other means e.g. from an external system
    */
    public MyUserDetailsService() {
        users.add(new UserObject("Eric", "123", "ADMIN"));
        users.add(new UserObject("Mike", "321", "USER"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserObject> user = users.stream()
            .filter(u->u.username.equals(username))
            .findAny();

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user.get());
    }

    private UserDetails toUserDetails(UserObject userObject) {
        return User.withUsername(userObject.username)
            .password(new BCryptPasswordEncoder().encode(userObject.password))
            .roles(userObject.role).build();
    }

    private static class UserObject {
        private String username;
        private String password;
        private String role;

        public UserObject(String name, String password, String role) {
            this.username = name;
            this.password = password;
            this.role = role;
        }
    }
}
