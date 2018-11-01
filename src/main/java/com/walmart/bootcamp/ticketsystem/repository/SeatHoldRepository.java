package com.walmart.bootcamp.ticketsystem.repository;

import com.walmart.bootcamp.ticketsystem.model.SeatHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SeatHoldRepository extends JpaRepository<SeatHold, Integer> {
      /*  @Modifying
        @Transactional
        @Query("update SeatHold s set s.hold = :holdStatus where s.seatId = :seatId")
        int resetSeatsHold(@Param("holdStatus") Boolean holdStatus, @Param("seatId") Integer seatId);*/


        @Modifying
        @Transactional
        @Query("update SeatHold s set s.reserved = :reserveStatus where s.seatId = :seatId")
        int resetSeatsReserved(@Param("reserveStatus") Boolean reserveStatus, @Param("seatId") Integer seatId);


        @Query("select count(s.seatId) from SeatHold s where s.hold is false")
        int numSeatsAvailable();

        @Modifying
        @Transactional
        @Query(nativeQuery = true, value = "update seats s set s.hold = :holdStatus, s.customer_email = :customerEmail where s.hold is false order by s.seat_id limit :numSeats" )
        int resetSeatsHold( @Param("holdStatus") Boolean holdStatus, @Param("customerEmail") String customerEmail , @Param("numSeats") Integer numSeats);


}
