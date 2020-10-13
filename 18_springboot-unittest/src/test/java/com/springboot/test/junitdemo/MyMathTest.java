package com.springboot.test.junitdemo;

import com.springboot.test.junitdemo.MyMath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

//Jupiter
//https://sormuras.github.io/blog/2018-09-13-junit-4-core-vs-jupiter-api.html
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyMathTest {
    MyMath myMath = new MyMath();

    /*
    @AfterEach is used to signal that the annotated method should be executed
    after each @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, and @TestTemplate
    method in the current test class.
     */
    @AfterEach
    public void AfterEach() {
        System.out.println("After each @Test");
    }

    @AfterAll
    public void AfterAll() {
        System.out.println("After current test class");
    }

    @Test
    public void sum_with3numbers() {
        System.out.println("Test 3 numbers");
        assertEquals(6, myMath.sum(new int[]{1, 2, 3}));
    }

    @Test
    public void sum_with1number() {
        System.out.println("Test 1 number");
        assertEquals(3, myMath.sum(new int[]{3}));
    }
}