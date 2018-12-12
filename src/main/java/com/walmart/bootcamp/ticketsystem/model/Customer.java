package com.walmart.bootcamp.ticketsystem.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
        private static final long serialVersionUID = -7893458373970990716L;
        private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "customer_id", columnDefinition = "integer", nullable = false)
        private Long customerId;

        @Column(name = "customer_email", nullable = false, columnDefinition = "varchar")
        private String customerEmail;
}
