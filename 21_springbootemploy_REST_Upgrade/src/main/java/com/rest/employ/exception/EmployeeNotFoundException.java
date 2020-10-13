package com.rest.employ.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(long id) {
        super("Could not find employee " + id);
    }
}
