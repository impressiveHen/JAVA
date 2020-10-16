package com.security.mysql.service;

import com.security.mysql.config.MyAuthenticationSuccessHandler;
import com.security.mysql.entity.Role;
import com.security.mysql.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(userName);

        logger.error("MyUserDetailsService: " + userName);

        if (user == null) {
            throw new UsernameNotFoundException("User: " + userName + " is not registered");
        }

        List<GrantedAuthority> authorities = getUserAuthorites(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthorites(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.stream().forEach(role->roles.add(new SimpleGrantedAuthority(role.getRole())));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
            user.getPassword(),
            authorities
        );
    }
}
