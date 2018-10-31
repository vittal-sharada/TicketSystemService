package com.walmart.bootcamp.ticketsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {"com.walmart.bootcamp.ticketsystem"})
public class TicketSystem {
        private static final Logger LOGGER = LoggerFactory.getLogger(TicketSystem.class);
        public static void main(String[] args) {

                SpringApplication.run(TicketSystem.class, args);

        }

}
