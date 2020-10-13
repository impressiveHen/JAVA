package com.rest.employ;

import com.rest.employ.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Order(value=2)
@Component
public class RestTemplateCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("///////////////////////////////////////////////////");
        getEmployees();
        getEmployee(1);
        createEmployee("Shiang", "Hu", "Software Engineer");
    }

    private void getEmployees() {
        final String uri = "http://localhost:8080/employees";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    }

    private void getEmployee(long id) {
        final String uri = "http://localhost:8080/employees/" + String.valueOf(id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.getForEntity(uri, Employee.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }

    private void createEmployee(String firstName, String lastName, String role) {
        final String uri = "http://localhost:8080/employees/";
        RestTemplate restTemplate = new RestTemplate();
        Employee newEmployee = new Employee(firstName, lastName, role);
        ResponseEntity<Employee> response = restTemplate.postForEntity(uri, newEmployee, Employee.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }
}
