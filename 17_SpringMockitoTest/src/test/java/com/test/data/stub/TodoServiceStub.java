package com.test.data.stub;

import com.test.data.TodoService;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC", "Learn Spring",
                "Learn to Dance");
    }

    public void deleteTodo(String todo) {

    }
}