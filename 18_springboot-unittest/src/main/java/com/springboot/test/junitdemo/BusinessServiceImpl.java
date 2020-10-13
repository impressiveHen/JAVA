package com.springboot.test.junitdemo;

public class BusinessServiceImpl {
    private DataService dataService;

    public BusinessServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    public int getMaxFromData() {
        int[] data = dataService.retrieveData();
        int max = Integer.MIN_VALUE;
        for (int i=0; i<data.length; i++) {
            max = Math.max(data[i], max);
        }
        return max;
    }

}
