package com.walmart.bootcamp.ticketsystem.model;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class SeatHold implements Serializable {
        private static final long serialVersionUID = -7893458373970990715L;
        private static final Logger LOGGER = LoggerFactory.getLogger(SeatHold.class);

        Integer seatHoldId;
        ArrayList<Integer> seatId;
        String customerEmail;
        String reservationId;
}
