package com.wtix.wtix.service;

import com.wtix.wtix.model.Cinema;
import com.wtix.wtix.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> getAll(){
        return cinemaRepository.findAll();
    }

    public List<Cinema> searchCinemas(String query) {
        return cinemaRepository.findByNameContainingIgnoreCase(query);
    }

}
