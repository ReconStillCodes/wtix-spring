package com.wtix.wtix.service;

import com.wtix.wtix.factory.ScreeningTypeFactory;
import com.wtix.wtix.model.Screening;
import com.wtix.wtix.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningService {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private ScreeningTypeFactory screeningTypeFactory;

    public List<Screening> getScreeningByMovieId(Integer movieId){
        List<Screening> screenings = screeningRepository.findByMovieId(movieId);

        for(Screening s : screenings){
//            System.out.println("Screening Type id = " + s.getScreeningTypeId());
            s.setScreeningType(screeningTypeFactory.createScreeningType(s.getScreeningTypeId()));
        }
        return screenings;
    }

    public List<Screening> getScreeningByCinemaAndScreenType(Integer cinemaId, Integer screenTypeId){
        return screeningRepository.findByCinemaIdAndScreeningTypeId(cinemaId, screenTypeId);
    }

    public List<Screening> getScreeningsByCinemaAndDate(Integer cinemaId, LocalDate currDate) {

        LocalDateTime minDateTime = currDate.atStartOfDay();
        LocalDateTime maxDateTime = currDate.atTime(LocalTime.MAX);

        List<Screening> screenings = screeningRepository.findByCinemaIdAndStartTimeBetween(cinemaId, minDateTime, maxDateTime);

        Collections.sort(screenings, Comparator.comparing(Screening::getStartTime));

        return screenings;
    }

    public Screening getScreeningById(Integer id){
        Optional<Screening> screening = screeningRepository.findById(id);
        Screening s = null;

        if(screening.isPresent()){
            s = screening.get();
            s.setScreeningType(screeningTypeFactory.createScreeningType(s.getScreeningTypeId()));
        }
        return s;
    }

}
