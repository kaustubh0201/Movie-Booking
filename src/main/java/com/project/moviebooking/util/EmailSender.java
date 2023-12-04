package com.project.moviebooking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Utility class to handle sending OTPs via email.
 */
@Component
public class EmailSender {

    /**
     * JavaMailSender instance to send emails.
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Sends an OTP to the provided email address for account verification.
     *
     * @param email The recipient's email address
     * @param otp   The OTP to be sent
     */
    public void sendOTP(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);
        mailMessage.setSubject("Movie-Booking OTP Verification");
        mailMessage.setText("Hello!\n\n" +
                "Thank you for signing up with Movie-Booking.\n" +
                "Here's the OTP for your account verification: " + otp + "." + "\n\n" +
                "Regards, \nTeam Movie-Booking");

        javaMailSender.send(mailMessage);
    }

    /**
     * Sends an OTP to the provided email address for password reset.
     *
     * @param email The recipient's email address
     * @param otp   The OTP to be sent
     */
    public void sendForgetPasswordOTP(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setText(email);
        mailMessage.setSubject("Forget Password OTP Verification");
        mailMessage.setText("Hello!\n\n" +
                "Here's the OTP for forget password: " + otp + "." + "\n\n" +
                "Regards, \nTeam Movie-Booking");

        javaMailSender.send(mailMessage);
    }
}