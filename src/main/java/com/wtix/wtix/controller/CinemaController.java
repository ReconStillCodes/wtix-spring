package com.wtix.wtix.controller;

import com.wtix.wtix.model.Cinema;
import com.wtix.wtix.service.CinemaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cinema")
public class CinemaController extends SessionController{
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("")
    public String viewCinema(Model model, HttpSession session){
        if(!validateSession(session)){
            return failedSessionHandler();
        }

        List<Cinema> cinemas = cinemaService.getAll();
        model.addAttribute("cinemas", cinemas);
        return "cinema";
    };

    @GetMapping("/search")
    public String viewCinemaSearch(@RequestParam(value = "search", required = false) String search, Model model){
        List<Cinema> cinemas;

        if(search != null && !search.isEmpty()){
            cinemas = cinemaService.searchCinemas(search);
        }
        else{
            cinemas = cinemaService.getAll();
        }

        model.addAttribute("cinemas", cinemas);
        return "cinema";
    };

}
