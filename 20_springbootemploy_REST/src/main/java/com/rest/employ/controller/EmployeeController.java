package com.rest.employ.controller;

import com.rest.employ.entity.Employee;
import com.rest.employ.exception.EmployeeNotFoundException;
import com.rest.employ.service.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeModelAssembler employeeModelAssembler;
    /*
    CollectionModel<> is Spring HATEOAS container to encapsulate collections. It
    lets you include links. Since we’re talking REST, it should encapsulate
    collections of employee resources. That’s why you fetch all the employees,
    but then transform them into a list of EntityModel<Employee> objects.
     */
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
          .map(employeeModelAssembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    /*
    To be able to convert the JSON sent as HTTP Body content into a Java object
    which we can use in our application we need to use the @RequestBody annotation
    for the method argument. When we use the @RequestBody to annotate the method
    argument we are telling the framework to convert the JSON or XML payload which
    is in the request body of HTTP request into the object of a given type.
     */
    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable long id) {
        /*
        The orElseThrow() method of java.util.Optional class in Java is used to get
        the value of this Optional instance if present. If there is no value present
        in this Optional instance, then this method throws the exception generated
        from the specified supplier.
         */

        Employee employee = employeeRepository.findById(id)
            .orElseThrow(()->new EmployeeNotFoundException(id));

        return employeeModelAssembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id) {
        return employeeRepository.findById(id).map(employee->{
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return employeeRepository.save(employee);
        }).orElseGet(()-> {
            newEmployee.setId(id);
            return employeeRepository.save(newEmployee);
        });
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeRepository.deleteById(id);
    }
}
