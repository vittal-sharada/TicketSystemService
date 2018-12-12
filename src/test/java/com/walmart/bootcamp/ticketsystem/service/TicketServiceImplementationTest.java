package com.walmart.bootcamp.ticketsystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketServiceImplementationTest {

        @Autowired
        private TicketServiceImplementation ticketServiceImplementation;

        @Test
        public void numSeatsAvailable() {
                assertThat(ticketServiceImplementation.numSeatsAvailable()).isEqualTo(5);
        }

        @Test
        public void findAndHoldSeats() {
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