package com.walmart.bootcamp.ticketsystem.controller;


import com.walmart.bootcamp.ticketsystem.service.TicketServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

        @Autowired
        private TicketServiceInf ticketService;

        @RequestMapping("/getSeats")
        public int getSeats() {
                return ticketService.numSeatsAvailable();
        }

        @RequestMapping("/findAndHoldSeats/{numSeats}/email/{email}")
        public String findAndHoldSeats(@PathVariable int numSeats, @PathVariable String email) {
              /*  SeatHold sh =  ticketService.findAndHoldSeats(numSeats,email);
                if(sh.getHoldId().isEmpty()) {
                        return "Sorry, we are completely booked";
                } else {
                        return "Reservation successful, your reservation id is:"+ sh.getHoldId();
                }*/
                return null;
        }


}