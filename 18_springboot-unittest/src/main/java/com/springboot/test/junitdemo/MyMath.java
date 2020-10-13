package com.springboot.test.junitdemo;

import java.util.Arrays;

public class MyMath {
    public int sum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }
}
