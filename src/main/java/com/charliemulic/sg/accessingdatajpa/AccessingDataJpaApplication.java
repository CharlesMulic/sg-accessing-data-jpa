package com.charliemulic.sg.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "Bauer"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "test"));
            repository.save(new Customer("Michelle", "Bauer"));

            log.info("Customers found with findAll():");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            repository.findById(1L).ifPresent(customer -> {
                log.info("Customer found with findById(1L)");
                log.info(customer.toString());
            });

            log.info("");
            log.info("Customers found with findByLastName():");
            repository.findByLastName("test").forEach(customer -> log.info(customer.toString()));
        };
    }
}

