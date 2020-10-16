package com.security.mysql.repository;

import com.security.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
