package com.springboot.test.junitdemo;

import org.springframework.stereotype.Repository;

@Repository
public class SpringDataService {
    public int[] retrieveData() {
        return new int[]{1, 2, 3, 6};
    }
}
