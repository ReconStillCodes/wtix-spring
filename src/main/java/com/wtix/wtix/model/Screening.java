package com.wtix.wtix.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false, referencedColumnName = "id")
    private Cinema cinema;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false, referencedColumnName = "id")
    private Movie movie;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "screening_type_id", nullable = false)
    private Integer screeningTypeId;

    @Transient
    private ScreeningType screeningType;

    public Screening(){};

    public Screening(LocalDateTime startTime, Movie movie, Cinema cinema, Integer screeningTypeId) {
        this.screeningTypeId = screeningTypeId;
        this.startTime = startTime;
        this.movie = movie;
        this.cinema = cinema;
    }


    public Integer getScreeningTypeId() {
        return screeningTypeId;
    }

    public void setScreeningTypeId(Integer screeningTypeId) {
        this.screeningTypeId = screeningTypeId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setScreeningType(ScreeningType screeningType) {
        this.screeningType = screeningType;
    }

    public ScreeningType getScreeningType(){
        return screeningType;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", cinema=" + cinema +
                ", movie=" + movie +
                ", startTime=" + startTime +
                ", screeningTypeId=" + screeningTypeId +
                ", screeningType=" + screeningType +
                '}';
    }
}
