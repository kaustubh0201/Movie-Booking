package com.project.moviebooking.service;

import com.project.moviebooking.dto.UserRequest;
import com.project.moviebooking.model.User;
import com.project.moviebooking.repository.UserRepository;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Utils utils;

    public void createUser(UserRequest userRequest) {

        User user = utils.userRequestToUserTransformer(userRequest);

        userRepository.save(user);
        log.info("User added to the database with {}", user.getUserId());

    }

    public void sendOTP(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);
        mailMessage.setSubject("Code-Sync OTP Verification");
        mailMessage.setText("Hello!\n\n" +
                "Thank you for signing up with Code-Sync.\n" +
                "Here's the OTP for your account verification: " + otp + "\n\n" +
                "Regards, \nTeam Code-Sync");

        javaMailSender.send(mailMessage);
    }

}
