package com.walmart.bootcamp.ticketsystem.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seats")
public class SeatHoldRequest implements Serializable {
        private static final long serialVersionUID = -7893458373970990715L;
        private static final Logger LOGGER = LoggerFactory.getLogger(SeatHold.class);

        @Id
        @Column(name = "seat_id", nullable = false, updatable = false)
        private Integer seatId;

        @Column(name = "seathold_id", columnDefinition = "int")
        private Integer seatHoldId;

        @Column(name = "customer_email", columnDefinition = "varchar")
        private String customerEmail;

        @Column(name = "show_id", nullable = false, updatable = false, columnDefinition = "long")
        private Long showId;

        @Column(name = "reserved", nullable = false, columnDefinition = "boolean")
        private Boolean reserved;

        @Column(name = "hold", nullable = false, columnDefinition = "boolean")
        private Boolean hold;

        @Column(name = "reservation_id", columnDefinition = "varchar")
        private String reservationId;
}