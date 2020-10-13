package com.rest.employ.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.rest.employ.entity.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    /*
    A critical ingredient to any RESTful service is adding links to relevant
    operations. To make your controller more RESTful, A critical ingredient to
    any RESTful service is adding links to relevant operations. To make your
    controller more RESTful,

    The return type of the method has changed from Employee to
    EntityModel<Employee>. EntityModel<T> is a generic container from Spring
    HATEOAS that includes not only the data but a collection of links.

    linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel() asks that
    Spring HATEOAS build a link to the EmployeeController 's one() method, and
    flag it as a self link.

    linkTo(methodOn(EmployeeController.class).all()).withRel("employees") asks
    Spring HATEOAS to build a link to the aggregate root, all(), and call it
    "employees".
     */
    @Override
    public EntityModel<Employee> toModel(Employee employee) {

        return EntityModel.of(employee, //
            linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}
