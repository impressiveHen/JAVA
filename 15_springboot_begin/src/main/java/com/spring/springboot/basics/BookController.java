package com.spring.springboot.basics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book(1, "Chinese 12", "Henry"));
    }
}
