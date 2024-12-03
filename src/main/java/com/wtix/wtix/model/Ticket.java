package com.wtix.wtix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false, referencedColumnName = "id")
    private Booking booking;

    @OneToOne
    @JoinColumn(name = "seat_id", nullable = false, referencedColumnName = "id")
    private Seat seat;

    public Ticket(){}

    public Ticket(Booking booking, Seat seat) {
        this.booking = booking;
        this.seat = seat;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
