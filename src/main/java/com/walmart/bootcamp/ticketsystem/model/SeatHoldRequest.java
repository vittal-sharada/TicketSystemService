package com.walmart.bootcamp.ticketsystem.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seats")
public class SeatHoldRequest implements Serializable {
        private static final long serialVersionUID = -7893458373970990715L;
        private static final Logger LOGGER = LoggerFactory.getLogger(SeatHold.class);

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        @Column(name = "seathold_id", nullable = false, unique = true, columnDefinition = "long")
        private Long seatHoldId;

        @Size(max = 30)
        @Column(name = "seat_id", nullable = false, updatable = false)
        private Integer seatId;

        @Column(name = "customer_email", columnDefinition = "varchar")
        private String customerEmail;

        @Column(name = "show_id", nullable = false, updatable = false, columnDefinition = "long")
        private Long showId;

        @Column(name = "reserved", nullable = false, columnDefinition = "boolean")
        private Boolean reserved;

        @Column(name = "hold", nullable = false, columnDefinition = "boolean")
        private Boolean hold;

        @Column(name = "reservation_id", unique = true, columnDefinition = "long")
        private Long reservationId;
}