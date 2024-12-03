package com.wtix.wtix.repository;

import com.wtix.wtix.model.Screening;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    List<Screening> findByMovieId(Integer movieId);

    List<Screening> findByCinemaIdAndScreeningTypeId(Integer cinemaId, Integer screeningTypeId);

    List<Screening> findByCinemaIdAndStartTimeBetween(Integer cinemaId, LocalDateTime startDateTime, LocalDateTime endDateTime);


}
