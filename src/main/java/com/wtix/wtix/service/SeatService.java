package com.wtix.wtix.service;

import com.wtix.wtix.model.Screening;
import com.wtix.wtix.model.Seat;
import com.wtix.wtix.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;

    public Seat getSeatForTheatre(Screening screening, Integer row, Integer col){
        Optional<Seat> seat = Optional.ofNullable(seatRepository.findByScreeningIdAndRowNumberAndColNumber(screening.getId(), row, col));

        Seat s = null;
        if(seat.isPresent()){
            s = seat.get();
        }

        return s;
    }

    public Seat getSeatById(Integer id){
        Optional<Seat> seat = seatRepository.findById(id);

        Seat s = null;
        if(seat.isPresent()){
            s = seat.get();
        }

        return s;
    }

    public void postSeat(Seat seat){
        seatRepository.save(seat);
    }
}
