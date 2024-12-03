package com.wtix.wtix.service;

import com.wtix.wtix.model.Movie;
import com.wtix.wtix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getCarouselMovies(){
        List<Movie> movies = movieRepository.findAll().stream().limit(6).toList();
        return movies;
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public List<Movie> searchMovie(String query){
        return movieRepository.findByTitleContainingIgnoreCase(query);
    }

    public Movie getMovieById(Integer id){
        return movieRepository.findById(id).orElseThrow();
    }

}
