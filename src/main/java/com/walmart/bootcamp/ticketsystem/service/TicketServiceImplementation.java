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
         *
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(TicketServiceImplementation.class);
        @Autowired
        private ShowsRepository showsRepository;

        @Autowired
        private SeatHoldRepository seatHoldRepository;

        @Override
        public int numSeatsAvailable() {
                if(seatHoldRepository !=null) {
                        return seatHoldRepository.numSeatsAvailable();
                }
                return 0;
        }

        /**
         * Find and hold the best available seats for a customer
         *
         * @param numSeats the number of seats to find and hold
         * @param customerEmail unique identifier for the customer
         * @return a SeatHold object identifying the specific seats and related information
         */

      /*  public boolean containsHold(Boolean holdStatus) {
                List<SeatHold> seatHoldList =  seatHoldRepository.checkSeatsHoldReserveStatus("dd");
                for(SeatHold o : this) {
                        if (o != null && o.getHold().equals(true)) {
                                return true;
                        }
                        return false;
                }
        } */

        @Override
        public void findAndHoldSeats(int numSeats, String customerEmail) throws IllegalAccessException {
                List<SeatHold> seatHoldList ;

                if (numSeats > 0 || !customerEmail.isEmpty()) {
                        if (numSeats > numSeatsAvailable()) {
                                LOGGER.error("ERROR: More seats requested than available");
                        }
                        else if (numSeats <= numSeatsAvailable()) {
                                // set the status of hold in db to true
                                seatHoldRepository.resetSeatsHold(true, customerEmail, numSeats);

                                TimerTask startTimeTask = new TimerTask() {
                                        public void run() {
                                                LOGGER.info("ERROR: Started Hold Timer");
                                        }
                                };

                                //If seat hold is true - start the timer
                                seatHoldList = seatHoldRepository.checkSeatsHoldReserveStatus(customerEmail);
                                if (seatHoldList != null) {
                                LOGGER.info("SHARADA:"+ seatHoldList.size());
                                        seatHoldList.stream().filter(SeatHold::getHold).forEach(s->System.out.println("Sharada---" +s.getHold()));

                                        if (seatHoldList.stream().filter(SeatHold::getHold).equals(true) &&
                                                (long) seatHoldList.size() == numSeats) {

                                                Timer timer = new Timer();
                                                timer.schedule(startTimeTask, 0);

                                                //Setup a task to run after 5mins
                                                TimerTask endTimeTask = new TimerTask() {
                                                        public void run() {
                                                                // Check if seat is reserved after 5 seconds
                                                                List<SeatHold> seatHoldEndTimerList = seatHoldRepository.checkSeatsHoldReserveStatus(customerEmail);
                                                                if (seatHoldEndTimerList != null) {
                                                                        if (seatHoldEndTimerList.stream().filter(SeatHold::getReserved).equals(false) &&
                                                                                (long) seatHoldEndTimerList.size() == numSeats) {
                                                                                seatHoldRepository.resetSeatsHold(false, customerEmail, numSeats);
                                                                        }
                                                                } else {
                                                                        LOGGER.error("ERROR: SeatHold object is null while ending timer");
                                                                }

                                                        }
                                                };

                                                timer.schedule(endTimeTask, 5);

                                        } else {
                                                LOGGER.error("ERROR: Error occured while holding seats - not all seats are on hold ");
                                        }

                                } else {
                                        LOGGER.error("ERROR: Error occured while holding seats - SeatHoldList is null");
                                }

                        } else {
                                LOGGER.error("ERROR: Error occured while holding seats - Unable to get number of seats");
                        }
                }
                else {
                        LOGGER.error("ERROR: Error occured while holding seats - Email is null or number of seats is incorrect");
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
