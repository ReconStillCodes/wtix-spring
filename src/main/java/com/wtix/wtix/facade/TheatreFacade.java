package com.wtix.wtix.facade;

import com.wtix.wtix.factory.SeatFactory;
import com.wtix.wtix.model.Screening;
import com.wtix.wtix.model.ScreeningType;
import com.wtix.wtix.model.Seat;
import com.wtix.wtix.service.ScreeningService;
import com.wtix.wtix.service.SeatService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Component
public class TheatreFacade {

    private final ScreeningService screeningService;
    private final SeatService seatService;
    private SeatFactory seatFactory;

    public TheatreFacade(ScreeningService screeningService, SeatService seatService){
        this.screeningService = screeningService;
        this.seatService = seatService;
        this.seatFactory = new SeatFactory();
    }

    public String getTheatrePage(Integer screeningId, Model model){
        Screening screening = screeningService.getScreeningById(screeningId);
        ScreeningType screeningType = screening.getScreeningType();
        Integer seatArrangement[][] = screeningType.getAllSeats();
        Map<Integer, Map<Integer, Seat>> seats = getSeats(screening, seatArrangement);



        model.addAttribute("screening", screening);
        model.addAttribute("screeningType", screeningType);
        model.addAttribute("seats", seats);
        return "theatre";

    }

    public Map<Integer, Map<Integer, Seat>> getSeats (Screening screening, Integer seatArrangement[][]){

        //Intializing Seats

        Map<Integer, Map<Integer, Seat>> seats = new HashMap<>();
        Integer rowNum = 1;
        Integer colNum = 1;

        for(int row = 0; row < seatArrangement.length; row++){
            Boolean isSeatRowExist = false;

            Map<Integer, Seat> seatsCol = new HashMap<>();

            for(int col = 0; col < seatArrangement[row].length; col++){
                if(seatArrangement[row][col] == 0){
                    seatsCol.put(col, null);
                    continue;
                }

                Seat seat = seatService.getSeatForTheatre(screening, row, col);
                if(seat == null){
                    seat = seatFactory.createSeat(screening, rowNum, colNum, "available", row, col);
                    seatService.postSeat(seat);
                }

                seatsCol.put(col, seat);
                // System.out.println(seat.getNumber());


                isSeatRowExist = true;
                colNum++;

            }
            colNum = 1;
            seats.put(row, seatsCol);

            if(isSeatRowExist){
                rowNum++;
            }
        }

        return seats;
    }

}
