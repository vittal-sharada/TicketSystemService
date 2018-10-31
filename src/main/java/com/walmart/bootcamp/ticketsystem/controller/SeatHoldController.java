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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value= "SeatHold Controller")
@RestController
public class SeatHoldController {
        private static final Logger LOGGER = LoggerFactory.getLogger(SeatHoldController.class);
        @Autowired
        private TicketServiceInf ticketService;

        @RequestMapping("/getSeats")
        @Timed( name = "seat.find.all")
        @ExceptionMetered(name = "seat.find.all.exception")
        public List<SeatHold> getAllSeats() {
                return ticketService.getAllSeats();
        }

        @RequestMapping("/getSeat/{seatId}")
        @Timed( name = "seat.get.seatId")
        @ExceptionMetered(name = "seat.get.seatId.exception")
        public SeatHold getSeat(@PathVariable Integer seatId) {
                return ticketService.getSeat(seatId);
        }

        @RequestMapping("/holdSeat/{seatId}")
        @Timed( name = "seat.hold.seatId")
        @ExceptionMetered(name = "seat.hold.seatId.exception")
        public void holdSeat(@PathVariable Integer seatId) {
                ticketService.holdSeat(seatId);
        }

        @RequestMapping("/reserveSeat/{seatId}")
        @Timed( name = "seat.reserve.seatId")
        @ExceptionMetered(name = "seat.reserve.seatId.exception")
        public void reserveSeat(@PathVariable Integer seatId) {
                ticketService.reserveSeat(seatId);
        }

        @RequestMapping("/holdSeat/{seatId}/customerId/{customerId}/customerEmail/{customerEmail}")
        @Timed( name = "seat.hold.customer.seatId")
        @ExceptionMetered(name = "seat.hold.customer.seatId.exception")
        public void holdSeatCustomer(@PathVariable Integer seatId, @PathVariable Integer customerId, @PathVariable Integer customerEmail) {
                ticketService.holdSeatCustomer(seatId, customerId, customerEmail);
        }

        @RequestMapping("/reserveSeat/{seatId}/customerId/{customerId}/customerEmail/{customerEmail}")
        @Timed( name = "seat.reserve.customer.seatId")
        @ExceptionMetered(name = "seat.reserve.customer.seatId.exception")
        public void reserveSeatCustomer(@PathVariable Integer seatId, @PathVariable Integer customerId, @PathVariable Integer customerEmail) {
                ticketService.reserveSeatCustomer(seatId,customerId, customerEmail);
        }
}