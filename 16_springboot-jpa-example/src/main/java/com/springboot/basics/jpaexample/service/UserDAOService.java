package com.springboot.basics.jpaexample.service;

import com.springboot.basics.jpaexample.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
/*
@Transactional annotation is used when you want the certain method/class
(=all methods inside) to be executed in a transaction.
Transactions means all or nothing.
*/
@Transactional
public class UserDAOService {
    @PersistenceContext
    private EntityManager entityManager;

    public long insert(User user) {
        entityManager.persist(user);
        return user.getId();
    }


}
