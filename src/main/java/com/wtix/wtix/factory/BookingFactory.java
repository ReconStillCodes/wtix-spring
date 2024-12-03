package com.wtix.wtix.factory;

import com.wtix.wtix.model.Booking;
import com.wtix.wtix.model.Screening;
import com.wtix.wtix.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

@Component
public class BookingFactory {
    public Booking createBooking(User user, Screening screening, double totalPrice, String paymentMethod){
        LocalDate currDate = LocalDate.now();
        String qr = createQr();
        return new Booking(user, screening, currDate, totalPrice, paymentMethod, qr);
    }

    private String createQr(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

}
