package com.walmart.bootcamp.ticketsystem.service;

import com.walmart.bootcamp.ticketsystem.model.SeatHold;
import com.walmart.bootcamp.ticketsystem.repository.SeatHoldRepository;
import com.walmart.bootcamp.ticketsystem.repository.ShowsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class TicketServiceImplementation implements TicketServiceInf {
        /**
         * The number of seats in the venue that are neither held nor reserved
         *
         * @return the number of tickets available in the venue
         *
         */
        private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TicketServiceImplementation.class);
        @Autowired
        private ShowsRepository showsRepository;

        @Autowired
        private SeatHoldRepository seatHoldRepository;

        @Override
        public int numSeatsAvailable() {
                return seatHoldRepository.numSeatsAvailable();
        }

        /**
         * Find and hold the best available seats for a customer
         *
         * @param numSeats the number of seats to find and hold
         * @param customerEmail unique identifier for the customer
         * @return a SeatHold object identifying the specific seats and related information
         */
        @Override
        public void findAndHoldSeats(int numSeats, String customerEmail) throws IllegalAccessException {
                SeatHold sh = null;
                FindAndHoldWaitTimer findAndHoldWaitTimer = new FindAndHoldWaitTimer();

                if(numSeats > numSeatsAvailable()) {
                     throw new IllegalAccessException("more seats requested");
                }
                if(numSeats <= numSeatsAvailable() ) {
                        seatHoldRepository.resetSeatsHold(true, customerEmail, numSeats);
                        TimerTask task = new TimerTask() {
                                public void run() {
                                        LOGGER.info("Started Hold Timer");
                                }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, 0);

                        TimerTask task2 = new TimerTask() {
                                public void run() {
                                        if(!seatHoldRepository.checkSeatsHold(customerEmail))
                                        seatHoldRepository.resetSeatsHold(false, customerEmail, numSeats);
                                }
                        };
                        Timer timer2 = new Timer();
                        timer2.schedule(task2, 5000);

                }
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
                seatHoldRepository.resetSeatsReserved(true,true, customerEmail);
                return String.valueOf(seatHoldRepository.checkSeatsReserve(customerEmail, true));
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
