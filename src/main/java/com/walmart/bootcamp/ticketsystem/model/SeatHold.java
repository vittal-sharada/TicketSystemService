package com.walmart.bootcamp.ticketsystem.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "seats")
public class SeatHold implements Serializable {
        private static final long serialVersionUID = -7893458373970990715L;
        private static final Logger LOGGER = LoggerFactory.getLogger(SeatHold.class);

        //@Id
        //@GeneratedValue(strategy = GenerationType.IDENTITY)
        //@Column(name = "show_seat_id", columnDefinition = "integer", nullable = false)
        //private Long showSeatId;

       // @Id
       // @Column(name = "seat_id", nullable = false, columnDefinition = "integer")

        @Id
        @Size(max = 30)
        @Column(name = "seat_id", nullable = false, unique = true, length = 30, columnDefinition = "varchar")
        private Integer seatId;

        @Column(name = "seat_rank", nullable = false, columnDefinition = "integer")
        private Integer seatRank;

       /* @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @Column(name = "customer_id", nullable = false, columnDefinition = "integer")
        private Long customerId;*/

        @OneToOne( fetch = FetchType.LAZY)
        @JoinColumn(name = "show_name", nullable = false)
        private Shows show;

        @Column(name = "show_name", updatable = false, insertable = false, columnDefinition = "varchar")
        private String showName;
}
