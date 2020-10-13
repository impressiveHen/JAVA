package com.springboot.test.junitdemo;

import org.springframework.stereotype.Component;

@Component
public class SpringBusinessServiceImpl {
    private SpringDataService springDataService;

    public SpringBusinessServiceImpl(SpringDataService springDataService) {
        this.springDataService = springDataService;
    }

    public int getMaxFromData() {
        int[] data = springDataService.retrieveData();
        int max = Integer.MIN_VALUE;
        for (int i=0; i<data.length; i++) {
            max = Math.max(data[i], max);
        }
        return max;
    }

}
