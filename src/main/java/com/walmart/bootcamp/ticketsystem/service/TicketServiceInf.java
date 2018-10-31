package com.walmart.bootcamp.ticketsystem.service;

import com.walmart.bootcamp.ticketsystem.model.SeatHold;
import com.walmart.bootcamp.ticketsystem.model.Shows;

import java.util.List;

public interface TicketServiceInf {

        /**
         * The number of seats in the venue that are neither held nor reserved
         *
         * @return the number of tickets available in the venue
         */
        int numSeatsAvailable();

        /**
         * Find and hold the best available seats for a customer
         *
         * @param numSeats      the number of seats to find and hold
         * @param customerEmail unique identifier for the customer
         * @return a SeatHold object identifying the specific seats and related information
         */
        SeatHold findAndHoldSeats(int numSeats, String customerEmail);

        /**
         * Commit seats held for a specific customer
         *
         * @param seatHoldId    the seat hold identifier
         * @param customerEmail the email address of the customer to which the seat hold is assigned
         * @return a reservation confirmation code
         */
        String reserveSeats(int seatHoldId, String customerEmail);


        List<Shows> getAllShows();

        void addShows(Shows show);

        Shows getShow(String showName);

        List<SeatHold> getAllSeats();

        void addSeats(SeatHold seat);

        SeatHold getSeat(Integer seatId);

        String holdSeat(Integer seatId);

        String reserveSeat(Integer seatId);

        void holdSeatCustomer(Integer seatId, Integer customerId, Integer customerEmail);

        void reserveSeatCustomer(Integer seatId, Integer customerId, Integer customerEmail);
}