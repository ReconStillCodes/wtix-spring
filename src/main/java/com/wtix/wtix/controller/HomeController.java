package com.wtix.wtix.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wtix.wtix.model.Movie;
import com.wtix.wtix.service.MovieService;

@Controller
@RequestMapping("/home")
public class HomeController extends SessionController{
    @Autowired
    MovieService movieService;


    @GetMapping("")
    public String viewHome(Model model, HttpSession session){
        if(!validateSession(session)){
            return failedSessionHandler();
        }

        List<Movie> movies = movieService.getCarouselMovies();
        model.addAttribute("movies", movies);
        return "home";
    };

    
}
