package com.wtix.wtix.facade;

import com.wtix.wtix.model.Booking;
import com.wtix.wtix.model.Ticket;
import com.wtix.wtix.model.User;
import com.wtix.wtix.service.BookingService;
import com.wtix.wtix.service.TicketService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TicketFacade {

    private final BookingService bookingService;
    private final TicketService ticketService;

    public TicketFacade(BookingService bookingService, TicketService ticketService){
        this.bookingService = bookingService;
        this.ticketService = ticketService;
    }

    public void getActiveTickets(User user, Model model){
        Map<Integer, List<Ticket>> tickets = new HashMap<>();

       List<Booking> activeBookings = new ArrayList<>();
       activeBookings = bookingService.getActiveBookingByUserId(user);

       for(Booking b : activeBookings){
           List<Ticket> ts = new ArrayList<>();
           ts = ticketService.getTicketsByBookingId(b);
           tickets.put(b.getId(), ts);
       }

       model.addAttribute("activeBookings", activeBookings);
       model.addAttribute("activeTickets", tickets);
    }

    public void getInactiveTickets(User user,  Model model){
        Map<Integer, List<Ticket>> tickets = new HashMap<>();

        List<Booking> inactiveBookings = new ArrayList<>();
        inactiveBookings = bookingService.getInactiveBookingByUserId(user);

        for(Booking b : inactiveBookings){
            List<Ticket> ts = new ArrayList<>();
            ts = ticketService.getTicketsByBookingId(b);
            tickets.put(b.getId(), ts);
        }

        model.addAttribute("inactiveBookings", inactiveBookings);
        model.addAttribute("inactiveTickets", tickets);
    }

}
