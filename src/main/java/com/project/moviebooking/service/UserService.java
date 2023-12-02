package com.project.moviebooking.service;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.dto.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<AuthenticationResponse> createUser(UserRequest userRequest);
    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request);
    ResponseEntity<AuthenticationResponse> verifyUser(String emailId, String otp);
    ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response);
    ResponseEntity<AuthenticationResponse> forgotPassword(String emailId);
    ResponseEntity<AuthenticationResponse> verifyForgetPassword(String emailId, String otp, String password);
    ResponseEntity<AuthenticationResponse> sendOtpAgain(String emailId);
}
