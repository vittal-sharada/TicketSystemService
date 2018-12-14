package com.walmart.bootcamp.ticketsystem.repository;

import com.walmart.bootcamp.ticketsystem.model.SeatHoldRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SeatHoldRepository extends JpaRepository<SeatHoldRequest, Integer> {

        @Query("select count(s.seatId) from SeatHoldRequest s where s.hold is false and s.reserved is false")
        int numSeatsAvailable();

        @Modifying
        @Transactional
        @Query(nativeQuery = true, value = "update seats s set s.hold = :holdStatus, s.customer_email = :customerEmail, s.seatHold_id =:seatHoldId where s.hold is false order by s.seat_id limit :numSeats" )
        int seatsHold( @Param("holdStatus") Boolean holdStatus, @Param("customerEmail") String customerEmail , @Param("seatHoldId") Integer seatHoldId, @Param("numSeats") Integer numSeats);

        @Modifying
        @Transactional
        @Query(nativeQuery = true, value = "update seats s set s.hold = :holdStatus and s.customer_email = '' and s.reserved = false where s.customer_email = :customerEmail" )
        int seatsHoldReset( @Param("holdStatus") Boolean holdStatus, @Param("customerEmail") String customerEmail );


        @Query(nativeQuery = true, value = "select * from seats s where s.customer_email = :customerEmail" )
        List<SeatHoldRequest> checkSeatsHoldReserveStatus(@Param("customerEmail") String customerEmail );

        @Modifying
        @Transactional
        @Query(nativeQuery = true, value = "update seats s set s.reserved = :reservedStatus, s.reservation_id = :reservationId  where s.customer_email = :customerEmail and s.hold = :holdStatus and s.seathold_id =:seatHoldId " )
        int reserveSeatQuery(@Param("reservedStatus") Boolean reservedStatus, @Param("reservationId") String reservationId, @Param("holdStatus") Boolean holdStatus, @Param("customerEmail") String customerEmail, @Param("seatHoldId") int seatHoldId);

        //@Modifying
        //@Transactional
      //  @Query(nativeQuery = true, value = "update seats s set s.reserved = true where s.customer_email = :cusomterEmail and s.hold= 'false' and s.seat_hold_id IN {}" )
      //  int reserveSeat( @Param("customerEmail") String customerEmail, @Param("seatHoldId") Long seatHoldId);
}
