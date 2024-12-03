package com.wtix.wtix.service;

import com.wtix.wtix.factory.TicketFactory;
import com.wtix.wtix.model.Booking;
import com.wtix.wtix.model.Seat;
import com.wtix.wtix.model.Ticket;
import com.wtix.wtix.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;


    public List<Ticket> createTickets(List<Seat> seats, Booking booking){
        TicketFactory ticketFactory = new TicketFactory();
        List<Ticket> tickets = ticketFactory.createTickets(seats, booking);
        for(Ticket t : tickets){
            ticketRepository.save(t);
        }

        return tickets;
    }

    public List<Ticket> getTicketsByBookingId(Booking booking){
        return ticketRepository.findByBookingId(booking.getId());
    }
}
