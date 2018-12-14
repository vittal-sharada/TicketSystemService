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

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketServiceImplementationTest {

        @Autowired
        private TicketServiceImplementation ticketServiceImplementation;

        @Autowired
        private SeatHoldRepository seatHoldRepository;

        //@Autowired
        private SeatHold sh = new SeatHold();

        private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImplementationTest.class);

        @Test
        public void numSeatsAvailable() {
                assertThat(ticketServiceImplementation.numSeatsAvailable()).isEqualTo(5);
        }

        @Test
        public void findAndHoldSeats() {

                try {
                       // ticketServiceImplementation.findAndHoldSeats(0,"");
                       // ticketServiceImplementation.findAndHoldSeats(10,"sharada@gmail.com");
                       // ticketServiceImplementation.findAndHoldSeats(2,"sharada@gmail.com");
                       sh  = ticketServiceImplementation.findAndHoldSeats(2,"sharada@gmail.com");


                        LOGGER.info("-----------Waiting 7 seconds----------");

                        try {
                                Thread.sleep(7000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }

                        assertThat(ticketServiceImplementation.numSeatsAvailable()).isEqualTo(5);
                        LOGGER.info("LIST:"+ sh.getSeatHoldIdlist().size());


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