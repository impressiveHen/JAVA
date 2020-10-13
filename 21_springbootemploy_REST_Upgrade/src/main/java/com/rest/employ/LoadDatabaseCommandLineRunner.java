package com.rest.employ;

import com.rest.employ.controller.OrderRepository;
import com.rest.employ.controller.Status;
import com.rest.employ.entity.Employee;
import com.rest.employ.entity.CustumerOrder;
import com.rest.employ.service.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value=1)
@Component
public class LoadDatabaseCommandLineRunner implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabaseCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Preloading" + employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar")));
        log.info("Preloading" + employeeRepository.save(new Employee("Frodo", "Baggins", "thief")));

        orderRepository.save(new CustumerOrder("MacBook Pro", Status.COMPLETED));
        orderRepository.save(new CustumerOrder("iPhone", Status.IN_PROGRESS));

        orderRepository.findAll().forEach(order -> {
            log.info("Preloaded " + order);
        });
    }
}
