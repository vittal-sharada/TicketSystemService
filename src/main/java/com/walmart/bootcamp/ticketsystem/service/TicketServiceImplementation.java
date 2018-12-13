package com.walmart.bootcamp.ticketsystem.service;

import com.walmart.bootcamp.ticketsystem.model.SeatHold;
import com.walmart.bootcamp.ticketsystem.repository.SeatHoldRepository;
import com.walmart.bootcamp.ticketsystem.repository.ShowsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class TicketServiceImplementation implements TicketServiceInf {
        /**
         * The number of seats in the venue that are neither held nor reserved
         *
         * @return the number of tickets available in the venue
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImplementation.class);
        @Autowired
        private ShowsRepository showsRepository;

        @Autowired
        private SeatHoldRepository seatHoldRepository;

        @Override
        public int numSeatsAvailable() {
                int numSeats=0;
                if (seatHoldRepository != null) {
                        numSeats = seatHoldRepository.numSeatsAvailable();
                        LOGGER.info("INFO: SeatsAvailable:"+ numSeats);
                        return numSeats;
                }
                return numSeats;
        }

        /**
         * Find and hold the best available seats for a customer
         *
         * @param numSeats      the number of seats to find and hold
         * @param customerEmail unique identifier for the customer
         * @return a SeatHold object identifying the specific seats and related information
         */

        @Override
        public void findAndHoldSeats(int numSeats, String customerEmail) throws IllegalAccessException {
                List<SeatHold> seatHoldList;
                int availableSeats = numSeatsAvailable();

                if (numSeats > 0 || !customerEmail.isEmpty()) {
                        if (numSeats > availableSeats) {
                                LOGGER.error("ERROR: More seats requested than available");
                        } else if (numSeats <= availableSeats) {
                                LOGGER.info("INFO: Set the status of hold in db to true");
                                int holdStatus = seatHoldRepository.seatsHold(true, customerEmail, numSeats);

                                TimerTask startTimeTask = new TimerTask() {
                                        public void run() {
                                                LOGGER.info("INFO: Started Hold Timer");
                                        }
                                };

                                LOGGER.info("INFO: If seat hold is true - start the timer");
                                if (holdStatus == numSeats) {
                                        Timer timer = new Timer();
                                        timer.schedule(startTimeTask, 0);

                                        TimerTask endTimeTask = new TimerTask() {
                                                public void run() {
                                                        LOGGER.info("INFO: Check if seat is reserved after 5 seconds");
                                                        List<SeatHold> seatHoldEndTimerList = seatHoldRepository.checkSeatsHoldReserveStatus(customerEmail);
                                                        if (seatHoldEndTimerList != null) {
                                                                if (checkReserveStatus(seatHoldEndTimerList) == 0) {
                                                                       int resetHold =  seatHoldRepository.seatsHoldReset(false, customerEmail);
                                                                       if(resetHold == numSeats) {
                                                                               LOGGER.info("INFO: Ended Hold Timer");
                                                                       }
                                                                       else {
                                                                               LOGGER.error("INFO: Ended Hold Timer, but something went wrong while updating DB");
                                                                       }
                                                                }
                                                        } else {
                                                                LOGGER.error("ERROR: SeatHold object is null while ending timer");
                                                        }
                                                }
                                        };
                                        long delay = 5000;
                                        timer.schedule(endTimeTask, delay);

                                } else {
                                        LOGGER.error("ERROR: Error occured while holding seats - SeatHoldList is null");
                                }

                        } else {
                                LOGGER.error("ERROR: Error occured while holding seats - Unable to get number of seats");
                        }
                } else {
                        LOGGER.error("ERROR: Error occured while holding seats - Email is null or number of seats is incorrect");
                }
        }



        private int checkReserveStatus(List<SeatHold> seatHoldEndTimerList) {
                int reserved=0;

                for( int i=0; i < seatHoldEndTimerList.size(); i++) {
                        if( seatHoldEndTimerList.get(i).getReserved() ) {
                                reserved = 1;
                        } else {
                                reserved = 0;
                                break;
                        }
                }
                LOGGER.info("Reservation status:"+ reserved);
                return reserved;

        }
        /**
         * Commit seats held for a specific customer
         *
         * @param seatHoldId    the seat hold identifier
         * @param customerEmail the email address of the customer to which the seat hold is assigned
         * @return a reservation confirmation code
         */
        @Override
        public String reserveSeats(int seatHoldId, String customerEmail) {
                //   seatHoldRepository.resetSeatsReserved(true,false, customerEmail, seatHoldId);
                //  return String.valueOf(seatHoldRepository.checkSeatsReserve(customerEmail, true));
                return "abc";
        }
/*
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
        public Shows getShow(String showName) {
                return  (Shows) this.showsRepository.findByShowName(showName);
        }

        @Override

        public List<SeatHold> getAllSeats() {
                List<SeatHold> seats = new ArrayList<SeatHold>();
                seatHoldRepository.findAll().forEach(seats::add);
                return seats;
        }

        @Override
        @Timed(name = "claim-service.create.claim")
        @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
        public void addSeats(SeatHold seat){
                seatHoldRepository.save(seat);
        }

        @Override
        public SeatHold getSeat(Integer seatId) {
                return (SeatHold) this.seatHoldRepository.findOne(seatId);
        }

        @Override
        public String holdSeat(Integer seatId) {
                //seatHoldRepository.resetSeatsHold(true, seatId);
                String response = "{\"success\": true, \"message\": Show has been added successfully.}";
                return response;

        }


        @Override
        public String reserveSeat(Integer seatId) {
                seatHoldRepository.resetSeatsReserved(true, seatId);
                String response = "{\"success\": true, \"message\": Show has been added successfully.}";
                return response;

        }

        @Override
        public void holdSeatCustomer(Integer seatId, Integer customerId, Integer customerEmail) {

        }

        @Override
        public void reserveSeatCustomer(Integer seatId, Integer customerId, Integer customerEmail) {

        }*/

}
