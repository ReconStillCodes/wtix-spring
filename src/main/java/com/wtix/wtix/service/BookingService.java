package com.wtix.wtix.service;

import com.wtix.wtix.factory.BookingFactory;
import com.wtix.wtix.model.Booking;
import com.wtix.wtix.model.Screening;
import com.wtix.wtix.model.User;
import com.wtix.wtix.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(User user, Screening screening, double totalPrice, String paymentMethod){
        BookingFactory bookingFactory = new BookingFactory();
        Booking booking = bookingFactory.createBooking(user, screening, totalPrice, paymentMethod);
        return bookingRepository.save(booking);
    }

    public List<Booking> getActiveBookingByUserId(User user){
        LocalDate currDate = LocalDate.now();
        return bookingRepository.findActiveBookingsByUserAndDate(user.getId(), currDate);
    }

    public List<Booking> getInactiveBookingByUserId(User user){
        LocalDate currDate = LocalDate.now();
        return bookingRepository.findInactiveBookingsByUserAndDate(user.getId(), currDate);
    }
}

