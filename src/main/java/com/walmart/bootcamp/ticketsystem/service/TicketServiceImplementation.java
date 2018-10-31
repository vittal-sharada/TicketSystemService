package com.walmart.bootcamp.ticketsystem.service;

import com.walmart.bootcamp.ticketsystem.model.SeatHold;
import com.walmart.bootcamp.ticketsystem.model.Shows;
import com.walmart.bootcamp.ticketsystem.repository.SeatHoldRepository;
import com.walmart.bootcamp.ticketsystem.repository.ShowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImplementation implements TicketServiceInf {
        /**
         * The number of seats in the venue that are neither held nor reserved
         *
         * @return the number of tickets available in the venue
         *
         */

        @Autowired
        private ShowsRepository showsRepository;

        @Autowired
        private SeatHoldRepository seatHoldRepository;

        @Override
        public int numSeatsAvailable() {
          return 0;
        }

        /**
         * Find and hold the best available seats for a customer
         *
         * @param numSeats the number of seats to find and hold
         * @param customerEmail unique identifier for the customer
         * @return a SeatHold object identifying the specific seats and related information
         */
        @Override
        public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
                SeatHold sh = null;
              /*  if(numSeats > availableSeats) {
                      sh = new SeatHold(0);
                      System.out.println(" more seats requested");
                      return sh;
                }
                if(numSeats <= availableSeats) {
                        sh = new SeatHold(numSeats, customerEmail);
                        availableSeats = availableSeats - numSeats;
                        System.out.println("seats available "+ sh.getHoldId());
                }*/

                return sh;
        }

        /**
         * Commit seats held for a specific customer
         *
         * @param seatHoldId the seat hold identifier
         * @param customerEmail the email address of the customer to which the seat hold is assigned
         * @return a reservation confirmation code
         */
        @Override
        public String reserveSeats(int seatHoldId, String customerEmail) {
                return "hi";
        }

        @Override
        public List<Shows> getAllShows() {
                List<Shows> shows = new ArrayList<Shows>();
                showsRepository.findAll().forEach(shows::add);
                return shows;
        }

        @Override
        public void addShows(Shows show){
                showsRepository.save(show);
        }

        @Override
        public Shows getShow(String showId) {
                return (Shows) this.showsRepository.findOne(showId);
        }

        @Override
        public List<SeatHold> getAllSeats() {
                List<SeatHold> seats = new ArrayList<SeatHold>();
                seatHoldRepository.findAll().forEach(seats::add);
                return seats;
        }

        @Override
        public void addSeats(SeatHold show){
                seatHoldRepository.save(show);
        }

        @Override
        public SeatHold getSeat(String seatId) {
                return (SeatHold) this.seatHoldRepository.findOne(seatId);
        }

}
