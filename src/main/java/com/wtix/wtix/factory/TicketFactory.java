package com.wtix.wtix.factory;

import com.wtix.wtix.model.Booking;
import com.wtix.wtix.model.Seat;
import com.wtix.wtix.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketFactory {
    public List<Ticket> createTickets(List<Seat> seats, Booking booking){
        List<Ticket> tickets = new ArrayList<>();
        for(Seat s : seats){
            Ticket t = new Ticket(booking, s);
            tickets.add(t);
        }
        return tickets;
    }

}
