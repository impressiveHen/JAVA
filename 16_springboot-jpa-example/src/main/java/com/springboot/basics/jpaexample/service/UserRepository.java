package com.springboot.basics.jpaexample.service;

import com.springboot.basics.jpaexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// <Entity, Id>
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
