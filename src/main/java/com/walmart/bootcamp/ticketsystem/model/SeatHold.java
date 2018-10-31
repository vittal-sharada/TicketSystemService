package com.walmart.bootcamp.ticketsystem.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seats")
public class SeatHold implements Serializable {
        private static final long serialVersionUID = -7893458373970990715L;
        private static final Logger LOGGER = LoggerFactory.getLogger(SeatHold.class);

        @Id
        @Size(max = 30)
        @Column(name = "seat_id", nullable = false, unique = true, length = 30, columnDefinition = "varchar")
        private Integer seatId;

        @Column(name = "customer_id", nullable = false, columnDefinition = "integer")
        private Long customerId;

        @Column(name = "customer_email", nullable = false, columnDefinition = "varchar")
        private String customerEmail;

        @Column(name = "show_id", updatable = false, insertable = false, columnDefinition = "long")
        private Long showId;

        @Column(name = "reserved", columnDefinition = "boolean")
        private Boolean reserved;

        @Column(name = "hold", columnDefinition = "boolean")
        private Boolean hold;
}
