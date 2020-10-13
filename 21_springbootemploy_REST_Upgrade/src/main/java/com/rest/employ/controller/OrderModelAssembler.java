package com.rest.employ.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.rest.employ.entity.CustumerOrder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/*
Enter HATEOAS or Hypermedia as the Engine of Application State. Instead of clients parsing the payload, give them
links to signal valid actions. Decouple state-based actions from the payload of data. In other words, when CANCEL
and COMPLETE are valid actions, dynamically add them to the list of links. Clients only need show users the
corresponding buttons when the links exist. This decouples clients from having to know WHEN such actions are valid,
reducing the risk of the server and its clients getting out of sync on the logic of state transitions.
 */
@Component
class OrderModelAssembler implements RepresentationModelAssembler<CustumerOrder, EntityModel<CustumerOrder>> {
    /*
    includes two conditional links to OrderController.cancel(id) as well as
    OrderController.complete(id) These links are ONLY shown when the order’s
    status is Status.IN_PROGRESS. If clients can adopt HAL and the ability to
    read links instead of simply reading the data of plain old JSON, they can trade in the need for domain
    knowledge about the order system.

    It checks the Order status before allowing it to be cancelled. If it’s not a valid state, it returns
    an RFC-7807 Problem, a hypermedia-supporting error container. If the transition is indeed valid, it
    transitions the Order to CANCELLED.
     */
    @Override
    public EntityModel<CustumerOrder> toModel(CustumerOrder order) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<CustumerOrder> orderModel = EntityModel.of(order,
            linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
            linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        // Conditional links based on state of the order

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderModel.add(linkTo(methodOn(OrderController.class).cancel(order.getId())).withRel("cancel"));
            orderModel.add(linkTo(methodOn(OrderController.class).complete(order.getId())).withRel("complete"));
        }

        return orderModel;
    }
}
