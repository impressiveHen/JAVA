package com.rest.employ.controller;

import com.rest.employ.entity.Employee;
import com.rest.employ.exception.EmployeeNotFoundException;
import com.rest.employ.service.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
migrate name -> firstname, lastname
do not delete db name field

You are now ready for an upgrade that will NOT disturb existing clients while
newer clients can take advantage of the enhancements!
 */

//The @Controller is a common annotation that is used to mark a class as
// Spring MVC Controller while @RestController is a special controller used
// in RESTFul web services and the equivalent of @Controller + @ResponseBody
@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeModelAssembler employeeModelAssembler;

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
          .map(employeeModelAssembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }


    /*
    ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can
    use it to fully configure the HTTP response.

    ResponseEntity.created:
    Create a new builder with a CREATED status (HTTP 201 Created) and a location header set to the given URI.

    getRequiredLink(): you can retrieve the Link created by the EmployeeModelAssembler with a SELF rel

    The Internet Assigned Numbers Authority contains a set of predefined link relations.
    They can be referred to via IanaLinkRelations, This method returns a Link which must be turned into a URI with
    the toUri

    With these tweaks in place, you can use the same endpoint to create a new employee resource,
    and use the legacy name field
    ex:
    curl -v -X POST localhost:8080/employees -H 'Content-Type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'
     */
    @PostMapping("/employees")
    public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(employeeRepository.save(newEmployee));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable long id) {

        Employee employee = employeeRepository.findById(id)
            .orElseThrow(()->new EmployeeNotFoundException(id));

        return employeeModelAssembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id) {
        Employee updatedEmployee = employeeRepository.findById(id)
            .map(employee -> {
                employee.setName(newEmployee.getName());
                employee.setRole(newEmployee.getRole());
                return employeeRepository.save(employee);
            })
            .orElseGet(()-> {
                newEmployee.setId(id);
                return employeeRepository.save(newEmployee);
            });

        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(updatedEmployee);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        employeeRepository.deleteById(id);
        //  HTTP 204 No Content
        return ResponseEntity.noContent().build();
    }
}
