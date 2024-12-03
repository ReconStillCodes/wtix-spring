package com.wtix.wtix.service;

import com.wtix.wtix.model.Booking;
import com.wtix.wtix.model.Movie;
import com.wtix.wtix.model.Screening;
import com.wtix.wtix.model.Ticket;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, Booking booking, Screening screening, List<Ticket> tickets){
        Movie movie = screening.getMovie();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String subject = "Wtix Booking Information";

        StringBuilder seatsBuilder = new StringBuilder();
        for (Ticket t : tickets) {
            seatsBuilder.append("<span>").append(t.getSeat().getNumber()).append("</span> ");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy hh:mm a");
        String formattedDate = screening.getStartTime().format(formatter);

        try{
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText("<html><body><h3>Booking ID "+booking.getId()+" Successfully booked!</h3>" +
                    "<p> Movie: "+ movie.getTitle() + "<p>"+
                    "<p> Cinema: "+ screening.getCinema().getName() + "<p> "+
                    "<p> Location: "+ screening.getCinema().getLocation() + "<p> "+
                    "<p> Date: "+ formattedDate + "<p> "+
                    "<p>Seats: " + seatsBuilder.toString() + "</p>" +
                    "<img src='https://quickchart.io/qr?text="+booking.getQr() +"&size=200' alt='qr'></body></html>",true);
            helper.setFrom("wtix378@gmail.com");
            javaMailSender.send(message);
            System.out.println("Email sent successfully!");
        }catch (MailException | MessagingException e){
            System.out.println("Error sending email.");
        }
    }
}
