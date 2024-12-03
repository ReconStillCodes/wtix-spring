package com.wtix.wtix.controller;

import com.wtix.wtix.facade.BuyMovieFacade;
import com.wtix.wtix.model.*;

import com.wtix.wtix.service.MovieService;
import com.wtix.wtix.service.ScreeningService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;


@Controller
@RequestMapping("/movie")
public class MovieController extends SessionController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreeningService screeningService;

    private BuyMovieFacade buyMovieFacade;

    public void init(){
        buyMovieFacade = new BuyMovieFacade(movieService, screeningService);
    }


    @GetMapping("")
    public String viewMovie(Model model, HttpSession session){
        if(!validateSession(session)){
            return failedSessionHandler();
        }

        List<Movie> movies = movieService.getAll();
        model.addAttribute("movies", movies);
        return "movie";
    };


    @GetMapping("/search")
    public String viewMovieSearch(@RequestParam(value = "search", required = false) String search, Model model){
        List<Movie> movies;

        if(search != null && !search.isEmpty()){
            movies = movieService.searchMovie(search);
        }else{
            movies = movieService.getAll();
        }

        model.addAttribute("movies", movies);
        return "movie";
    }

    @GetMapping("/movieId={id}/dateIdx={dateIdx}")
    public String viewBuyMovie(@PathVariable Integer id, @PathVariable Integer dateIdx, Model model){
        init();
        return buyMovieFacade.getBuyMoviePage(id, dateIdx, model);
    }


}
