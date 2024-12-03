package com.wtix.wtix.controller;

import com.wtix.wtix.facade.TicketFacade;
import com.wtix.wtix.model.Ticket;
import com.wtix.wtix.model.User;
import com.wtix.wtix.service.BookingService;
import com.wtix.wtix.service.TicketService;
import com.wtix.wtix.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ticket")
public class TicketController extends SessionController{

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @Autowired
    TicketService ticketService;

    private TicketFacade ticketFacade;

    public void init(){
        ticketFacade = new TicketFacade(bookingService, ticketService);
    }

    @GetMapping("")
    public String viewTicket(Model model, HttpSession session){
        if(!validateSession(session)){
            return failedSessionHandler();
        }

        init();
        User user = (User)session.getAttribute("user_session");
        //User user = userService.getUserById(1);

        ticketFacade.getActiveTickets(user, model);
        ticketFacade.getInactiveTickets(user, model);

        model.addAttribute("user", user);
        return "ticket";
    }


}
