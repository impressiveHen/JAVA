package com.helloworld.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // http://localhost:8080/hello?name=World
    @RequestMapping("/hello")
    public String sayHello(@RequestParam(value="name") String name) {
        return "Hello" + name + "!";
    }
}
