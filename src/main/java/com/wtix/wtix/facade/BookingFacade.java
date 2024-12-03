package com.wtix.wtix.facade;

import com.wtix.wtix.factory.ScreeningTypeFactory;
import com.wtix.wtix.model.*;
import com.wtix.wtix.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingFacade {

    private final SeatService seatService;
    private final BookingService bookingService;
    private final TicketService ticketService;
    private final EmailService emailService;

    public BookingFacade(SeatService seatService,  BookingService bookingService, TicketService ticketService, EmailService emailService){
        this.seatService = seatService;
        this.bookingService = bookingService;
        this.ticketService = ticketService;
        this.emailService = emailService;
    }

    public void handleBooking(List<String> seats, String paymentMethod,  HttpSession session){

       //Get Seat
        List<Seat> seatList = getSeatList(seats);

        //Get Screening
        Screening screening = seatList.get(0).getScreening();
        System.out.println(screening.toString());

        //Get TotalPrice
        ScreeningTypeFactory screeningTypeFactory = new ScreeningTypeFactory();
        double totalPrice = screeningTypeFactory.createScreeningType(screening.getScreeningTypeId()).getPrice();

        //Get User
        User user = (User)session.getAttribute("user_session");
        //User user = userService.getUserById(1); //Testing

        //Create Booking
        Booking booking = bookingService.createBooking(user, screening, totalPrice, paymentMethod);

        //Create Ticket
        List<Ticket> tickets = ticketService.createTickets(seatList, booking);

        //Send Email
        emailService.sendEmail(user.getEmail(), booking, screening, tickets);

    }

    public List<Seat> getSeatList(List<String> seats){
        List<Seat> seatList = new ArrayList<>();
        for(String sId : seats){
            Integer seatId = Integer.parseInt(sId);
            Seat seat = seatService.getSeatById(seatId);
            seat.setStatus("unavailable");
            seatList.add(seat);
        }
        return seatList;
    }

}
