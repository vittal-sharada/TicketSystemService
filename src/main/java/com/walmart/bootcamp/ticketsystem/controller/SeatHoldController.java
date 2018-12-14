package com.walmart.bootcamp.ticketsystem.controller;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.walmart.bootcamp.ticketsystem.model.SeatHold;
import com.walmart.bootcamp.ticketsystem.service.TicketServiceInf;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value= "SeatHold Controller")
@RestController
public class SeatHoldController {
        private static final Logger LOGGER = LoggerFactory.getLogger(SeatHoldController.class);

        @Autowired
        private TicketServiceInf ticketService;

        @RequestMapping("/getSeatsAvailable")
        @Timed( name = "seats.available.all")
        @ExceptionMetered(name = "seats.available.exception")
        public int numSeatsAvailable() {
                return ticketService.numSeatsAvailable();
        }

        @RequestMapping(value ="/holdSeat/{numSeats}/{customerEmail}", method = RequestMethod.POST)
        @Timed( name = "seat.hold.numSeats")
        @ExceptionMetered(name = "seat.hold.numSeats.exception")
        public int holdSeat(@PathVariable Integer numSeats, @PathVariable String customerEmail) {

                SeatHold sh = null;
                try {
                       sh =  ticketService.findAndHoldSeats(numSeats,customerEmail);
                        sh.getSeatHoldId();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                }
                return sh.getSeatHoldId();
        }

        @RequestMapping(value ="/reserveSeat/{seatHoldId}/{customerEmail}", method = RequestMethod.POST)
        @Timed( name = "seat.reserve.reserveSeat")
        @ExceptionMetered(name = "seat.reserve.reserveSeat.exception")
        public String reserveSeat(@PathVariable int seatHoldId, @PathVariable String customerEmail ) {
                return  ticketService.reserveSeats(seatHoldId, customerEmail);

        }

}