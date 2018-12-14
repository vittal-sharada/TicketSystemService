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
public class TicketServiceImplementationIT {

        @Autowired
        private TicketServiceImplementation ticketServiceImplementation;

        @Autowired
        private SeatHoldRepository seatHoldRepository;

        private SeatHold sh = new SeatHold();

        private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImplementationIT.class);

        @Test
        public void numSeatsAvailable() {
                assertThat(ticketServiceImplementation.numSeatsAvailable()).isEqualTo(5);
        }

        @Test
        public void findAndHoldSeatsWithZeroSeatAndEmptyEmail() {
                try {
                        sh = ticketServiceImplementation.findAndHoldSeats(0, "");
                        assertThat(sh.getCustomerEmail()).isEqualTo(null);
                        assertThat(sh.getSeatHoldId()).isEqualTo(null);
                }
                catch (IllegalAccessException e) {
                        e.printStackTrace();
                }
        }

        @Test
        public void findAndHoldSeatsWithMoreSeatsThanAvailable() {
                try {
                        sh = ticketServiceImplementation.findAndHoldSeats(10,"sharada@gmail.com");
                        assertThat(sh.getCustomerEmail()).isEqualTo(null);
                        assertThat(sh.getSeatHoldId()).isEqualTo(null);
                }
                catch (IllegalAccessException e) {
                        e.printStackTrace();
                }
        }


        @Test
        public void findAndHoldSeatsAndReserve() {
                try {
                        sh  = ticketServiceImplementation.findAndHoldSeats(2,"sharada@gmail.com");
                        String ff = ticketServiceImplementation.reserveSeats(sh.getSeatHoldId(), "sharada@gmail.com");
                        assertThat(ticketServiceImplementation.numSeatsAvailable()).isEqualTo(3);
                        assertThat(ff).isNotNull();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                }
        }

        @Test
        public void findAndHoldSeats() {
                try {
                        sh  = ticketServiceImplementation.findAndHoldSeats(2,"sharada@gmail.com");

                        LOGGER.info("-----------Waiting 20 seconds----------");
                        try {
                                Thread.sleep(20000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        assertThat(ticketServiceImplementation.numSeatsAvailable()).isEqualTo(5);
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                }
        }

        @Test
        public void reserveSeats() {

                //TODO
        }


}