package com.walmart.bootcamp.ticketsystem.service;

import com.walmart.bootcamp.ticketsystem.model.SeatHold;
import com.walmart.bootcamp.ticketsystem.repository.SeatHoldRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketServiceImplementationTest {

        @Autowired
        private TicketServiceImplementation ticketServiceImplementation;

        @Autowired
        private SeatHoldRepository seatHoldRepository;

        private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImplementationTest.class);

        @Test
        public void numSeatsAvailable() {
                assertThat(ticketServiceImplementation.numSeatsAvailable()).isEqualTo(5);
        }

        @Test
        public void findAndHoldSeats() {
                List<SeatHold> seatHoldList ;
                try {
                        ticketServiceImplementation.findAndHoldSeats(0,"");
                        ticketServiceImplementation.findAndHoldSeats(10,"sharada@gmail.com");
                        ticketServiceImplementation.findAndHoldSeats(2,"sharada@gmail.com");
                        seatHoldList = seatHoldRepository.checkSeatsHoldReserveStatus("sharada@gmail.com");

                       // Logger.getLogger(Level.ERROR);
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                }
        }

        @Test
        public void reserveSeats() {
        }

        @Test
        public void getAllShows() {
        }

        @Test
        public void addShows() {
        }

        @Test
        public void getShow() {
        }

        @Test
        public void getAllSeats() {
        }

        @Test
        public void addSeats() {
        }

        @Test
        public void getSeat() {
        }
}