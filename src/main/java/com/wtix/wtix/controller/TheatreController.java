package com.wtix.wtix.controller;

import java.util.List;

import com.wtix.wtix.facade.BookingFacade;
import com.wtix.wtix.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wtix.wtix.facade.TheatreFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/theatre")
public class TheatreController extends SessionController{

    @Autowired
    ScreeningService screeningService;

    @Autowired
    SeatService seatService;

    @Autowired
            UserService userService;

    @Autowired
            BookingService bookingService;

    @Autowired
            TicketService ticketService;

    @Autowired
            EmailService emailService;

    @Autowired
            MovieService movieService;


    TheatreFacade theatreFacade;
    BookingFacade bookingFacade;

    public void init(){
        theatreFacade = new TheatreFacade(screeningService, seatService);
        bookingFacade = new BookingFacade(seatService,  bookingService,  ticketService, emailService);
    }
    
    @GetMapping("/screeningId={screeningId}")
    public String viewTheatre(@PathVariable Integer screeningId, Model model, HttpSession session){
        if(!validateSession(session)){
            return failedSessionHandler();
        }
        init();
        return theatreFacade.getTheatrePage(screeningId, model);
    }

    @PostMapping("/buy")
    public String handleBuyTickets(@RequestParam(required = false) List<String> selectedSeats, @RequestParam(required = false) String paymentMethod, HttpSession session) {
        init();

        if (selectedSeats == null || selectedSeats.isEmpty() || selectedSeats.size() <= 0 || paymentMethod == null || paymentMethod.isEmpty()) {
            return "redirect:/movie";
        }
        bookingFacade.handleBooking(selectedSeats, paymentMethod, session);
        return "redirect:/movie";
    }
    
}
