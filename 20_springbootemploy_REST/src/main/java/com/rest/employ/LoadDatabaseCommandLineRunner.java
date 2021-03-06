package com.rest.employ;

import com.rest.employ.entity.Employee;
import com.rest.employ.service.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabaseCommandLineRunner implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabaseCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Preloading" + employeeRepository.save(new Employee("Bilbo", "burglar")));
        log.info("Preloading" + employeeRepository.save(new Employee("Frodo", "thief")));
    }
}
