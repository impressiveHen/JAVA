package com.rest.employ.controller;

import com.rest.employ.entity.CustumerOrder;
import com.rest.employ.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
You are modeling the flow of states between Status.IN_PROGRESS, Status.COMPLETED, and Status.CANCELLED
 */
@RestController
class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderModelAssembler orderModelAssembler;

    @GetMapping("/orders")
    CollectionModel<EntityModel<CustumerOrder>> all() {

        List<EntityModel<CustumerOrder>> orders = orderRepository.findAll().stream() //
            .map(orderModelAssembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(orders, //
            linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/orders/{id}")
    EntityModel<CustumerOrder> one(@PathVariable Long id) {

        CustumerOrder order = orderRepository.findById(id) //
            .orElseThrow(() -> new OrderNotFoundException(id));

        return orderModelAssembler.toModel(order);
    }

    @PostMapping("/orders")
    ResponseEntity<EntityModel<CustumerOrder>> newOrder(@RequestBody CustumerOrder order) {

        order.setStatus(Status.IN_PROGRESS);
        CustumerOrder newOrder = orderRepository.save(order);

        return ResponseEntity //
            .created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri()) //
            .body(orderModelAssembler.toModel(newOrder));
    }

    @DeleteMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {

        CustumerOrder order = orderRepository.findById(id) //
            .orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(orderModelAssembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity //
            .status(HttpStatus.METHOD_NOT_ALLOWED) //
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
            .body(Problem.create() //
                .withTitle("Method not allowed") //
                .withDetail("You can't cancel an order that is in the " + order.getStatus() + " status"));
    }

    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> complete(@PathVariable long id) {

        CustumerOrder order = orderRepository.findById(id) //
            .orElseThrow(() -> new OrderNotFoundException(id));

        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(orderModelAssembler.toModel(orderRepository.save(order)));
        }

        return ResponseEntity //
            .status(HttpStatus.METHOD_NOT_ALLOWED) //
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
            .body(Problem.create() //
                .withTitle("Method not allowed") //
                .withDetail("You can't complete an order that is in the " + order.getStatus() + " status"));
    }
}