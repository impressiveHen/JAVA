package com.rest.employ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
@ControllerAdvice is a specialization of the @Component annotation which allows
to handle exceptions across the whole application in one global handling component.
It can be viewed as an interceptor of exceptions thrown by methods annotated
with @RequestMapping and similar.
 */

@ControllerAdvice
public class EmployeeNotFoundAdvice {
    /*
    @ResponseBody signals that this advice is rendered straight into the response body.

    ExceptionHandler configures the advice to only respond if an EmployeeNotFoundException is thrown.

    @ResponseStatus says to issue an HttpStatus.NOT_FOUND, i.e. an HTTP 404.

    When an EmployeeNotFoundException is thrown, render an HTTP 404:
     */
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
