package com.project.moviebooking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOTP(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);
        mailMessage.setSubject("Movie-Booking OTP Verification");
        mailMessage.setText("Hello!\n\n" +
                "Thank you for signing up with Movie-Booking.\n" +
                "Here's the OTP for your account verification: " + otp + "." + ""\n\n" +
                "Regards, \nTeam Movie-Booking");

        javaMailSender.send(mailMessage);
    }
}
