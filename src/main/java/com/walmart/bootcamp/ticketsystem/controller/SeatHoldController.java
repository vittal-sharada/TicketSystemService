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
        @Timed( name = "seat.find.seatId")
        @ExceptionMetered(name = "seat.find.seatId.exception")
        public SeatHold getShow(@PathVariable String seatId) {
                return ticketService.getSeat(seatId);
        }
}