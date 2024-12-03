package com.wtix.wtix.factory;

import com.wtix.wtix.model.Screening;
import com.wtix.wtix.model.Seat;

public class SeatFactory {
    public Seat createSeat(Screening screening, Integer numberRow, Integer numberCol, String status, Integer rowNumber, Integer colNumber){

        char numberRowLetter = (char)('A' + numberRow - 1);
        String numberColDigit = String.valueOf(numberCol);
        String number = numberRowLetter + numberColDigit;
        Seat seat = new Seat(screening, number, status, rowNumber, colNumber);
        return seat;

    }
}
