package com.walmart.bootcamp.ticketsystem.controller;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.walmart.bootcamp.ticketsystem.model.Shows;
import com.walmart.bootcamp.ticketsystem.service.TicketServiceInf;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value= "Show Controller")
@RestController
public class ShowController {
        private static final Logger LOGGER = LoggerFactory.getLogger(ShowController.class);

        @Autowired
        private TicketServiceInf ticketService;

        @RequestMapping("/getShows")
        @ApiOperation(value = "/getShows")
        @Timed( name = "shows.find.all")
        @ExceptionMetered(name = "shows.find.all.exception")
        public List<Shows> getAllShows() {
                return ticketService.getAllShows();
        }

        @RequestMapping("/getShow/{showId}")
        @Timed( name = "shows.find.showId")
        @ExceptionMetered(name = "shows.find.showId.exception")
        public Shows getShow(@PathVariable String showName) {
                return ticketService.getShow(showName);
        }

        @RequestMapping(method = RequestMethod.POST, value="/addShows")
        public String addShows(@RequestBody Shows show) {
                ticketService.addShows(show);
                String response = "{\"success\": true, \"message\": Show has been added successfully.}";
                return response;
        }
}
