package com.wtix.wtix.facade;

import com.wtix.wtix.model.Cinema;
import com.wtix.wtix.model.Movie;
import com.wtix.wtix.model.Screening;
import com.wtix.wtix.model.ScreeningType;
import com.wtix.wtix.service.MovieService;
import com.wtix.wtix.service.ScreeningService;

import org.springframework.stereotype.Component;

import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class BuyMovieFacade {

    private final MovieService movieService;
    private final ScreeningService screeningService;


    public BuyMovieFacade(MovieService movieService, ScreeningService screeningService) {
        this.movieService = movieService;
        this.screeningService = screeningService;
    }

   public String getBuyMoviePage(Integer id, Integer dateIdx, Model model){
       //Movie
       Movie movie = movieService.getMovieById(id);

        //Duration
       String duration = getDuration(movie);

       //Screenings
       List<Screening> screenings = screeningService.getScreeningByMovieId(id);

       //Dates
       List<LocalDate> uniqueDates = getUniqueDates(screenings);
       List<String> formattedUniqueDate = getFormattedDates(uniqueDates);

       //cinemas
       LocalDate currDate = uniqueDates.get(dateIdx);
       List<Cinema> cinemas = getUniqueCinemas(screenings, currDate);

       //CurrScreenings
       List<Screening> currScreenings = getCurrScreenings(screenings, currDate);

       //ScreenType
       Map<Integer, List<ScreeningType>> screenTypes = getUniqueScreeningTypes(cinemas, currScreenings);
       Map<Integer, Map<Integer, List<Screening>>> screenTime = getScreensTime(screenTypes, cinemas, currScreenings);


       //add Model
       model.addAttribute("movie", movie);
       model.addAttribute("duration", duration);
       model.addAttribute("dates", formattedUniqueDate);
       model.addAttribute("currDateIdx", dateIdx);
       model.addAttribute("cinemas", cinemas);
       model.addAttribute("screenTypes", screenTypes);
       model.addAttribute("screenings", screenTime);

       return "buyMovie";
   }

   private String getDuration(Movie movie){
       Integer duration = movie.getDuration();
       Integer hour = duration / 60;
       Integer min = duration % 60;

       String durationStr;
       if(hour <= 0){
           durationStr  = min + " min";
       }else{
           durationStr = hour + " h " + min + " min";
       }
       return durationStr;
   }



    private List<LocalDate> getUniqueDates(List<Screening> screenings){
        List<LocalDate> uniqueDates = new ArrayList<>();

        for(Screening s : screenings){
            LocalDate date = s.getStartTime().toLocalDate();
            if(!uniqueDates.contains(date)){
                uniqueDates.add(date);
            }
        }

        uniqueDates.sort(Comparator.naturalOrder());
        return uniqueDates;
    }

    private List<String> getFormattedDates(List<LocalDate> dates){
        List<String> formattedUniqueDate = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM");

        for (LocalDate d : dates){
            String date = d.format(formatter);
            formattedUniqueDate.add(date);
        }

        return formattedUniqueDate;
    }

    private List<Cinema> getUniqueCinemas(List<Screening> screenings, LocalDate currDate){
        List<Cinema> cinemas = new ArrayList<>();

        for(Screening s : screenings){
            LocalDate date = s.getStartTime().toLocalDate();

            if(date.equals(currDate)){
                Cinema cinema = s.getCinema();

                if(!cinemas.contains(cinema)){
                    cinemas.add(cinema);
                }

            }
        }

        return cinemas;
    }

    private List<Screening> getCurrScreenings(List<Screening> screenings, LocalDate currDate){
        List<Screening> currScreenings = new ArrayList<>();

        for(Screening s : screenings){
            LocalDate date = s.getStartTime().toLocalDate();

            if(date.equals(currDate)){
                currScreenings.add(s);
            }
        }

        return currScreenings;
    }

    private boolean isExistInArray(List<ScreeningType> screeningTypes, ScreeningType target){
        for(ScreeningType st : screeningTypes){
            if(st.getId() == target.getId()) {
                return true;
            }
        }
        return false;
    }

    private Map<Integer, List<ScreeningType>> getUniqueScreeningTypes(List<Cinema> cinemas, List<Screening> currScreenings){
        Map<Integer, List<ScreeningType>> uniqueScreeningTypes = new HashMap<>();

        for(Cinema c : cinemas){
            List<ScreeningType> screeningTypes = new ArrayList<>();

            for(Screening s : currScreenings){
                Cinema cinema = s.getCinema();
                ScreeningType screeningType = s.getScreeningType();


                if(cinema != c)
                    continue;

                if(!isExistInArray(screeningTypes, screeningType)){
                    screeningTypes.add(screeningType);
                }
            }

            if(screeningTypes != null && !screeningTypes.isEmpty()){
                uniqueScreeningTypes.put(c.getId(), screeningTypes);
            }
        }

        return uniqueScreeningTypes;
    }


    private Map<Integer, List<Screening>> getScreensTimeST(List<ScreeningType> screeningTypes, Cinema cinema, List<Screening> currScreenings){

        Map<Integer, List<Screening>> screensTime = new HashMap<>();

        for(ScreeningType st : screeningTypes){

            List<Screening> screeningsST = new ArrayList<>();

            for(Screening s : currScreenings){
                if(s.getCinema().getId() != cinema.getId()){
                    continue;
                }

                ScreeningType screeningType = s.getScreeningType();
                if(st.getId() == screeningType.getId()){
                    screeningsST.add(s);
                }
            }

            if(!screeningsST.isEmpty()){
                screensTime.put(st.getId(), screeningsST);
            }
        }

        return screensTime;
    }

    private Map<Integer, Map<Integer, List<Screening>>> getScreensTime(Map<Integer, List<ScreeningType>> uniqueScreeningTypes, List<Cinema> cinemas, List<Screening> currScreenings){

        Map<Integer, Map<Integer, List<Screening>>> screensTime = new HashMap<>();

        for(Cinema c : cinemas){
            List<ScreeningType> screeningTypes = uniqueScreeningTypes.get(c.getId());
            Map<Integer, List<Screening>> screensTimeST = getScreensTimeST(screeningTypes, c, currScreenings);
            screensTime.put(c.getId(), screensTimeST);
        }

        return screensTime;
    }
}
