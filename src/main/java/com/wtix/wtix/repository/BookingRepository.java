package com.wtix.wtix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wtix.wtix.model.Booking;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND b.bookingTime >= :currentDateTime")
    List<Booking> findActiveBookingsByUserAndDate(
            @Param("userId") Integer userId,
            @Param("currentDateTime") LocalDate currentDateTime
    );

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND b.bookingTime < :currentDateTime")
    List<Booking> findInactiveBookingsByUserAndDate(
            @Param("userId") Integer userId,
            @Param("currentDateTime") LocalDate currentDateTime
    );
}
    

