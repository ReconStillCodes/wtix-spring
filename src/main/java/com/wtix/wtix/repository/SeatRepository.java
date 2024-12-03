package com.wtix.wtix.repository;

import com.wtix.wtix.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Seat findByScreeningIdAndRowNumberAndColNumber(Integer screeningId, Integer rowNumber, Integer colNumber);
}
