package com.walmart.bootcamp.ticketsystem.controller;

import com.walmart.bootcamp.ticketsystem.model.Shows;
import com.walmart.bootcamp.ticketsystem.service.TicketServiceInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowController {

        @Autowired
        private TicketServiceInf ticketService;

        @RequestMapping("/getShows")
        public List<Shows> getAllShows() {
                return ticketService.getAllShows();
        }

        @RequestMapping("/getShows/{id}")
        public Shows getShow(@PathVariable String id) {
                return ticketService.getShow(id);
        }

        @RequestMapping(method = RequestMethod.POST, value="/addShows")
        public String addShows(@RequestBody Shows show) {
                ticketService.addShows(show);
                String response = "{\"success\": true, \"message\": Show has been added successfully.}";
                return response;
        }
}
