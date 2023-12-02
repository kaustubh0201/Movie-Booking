package com.project.moviebooking.controller;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.dto.UserRequest;
import com.project.moviebooking.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody UserRequest userRequest)  {
        return userService.createUser(userRequest);
    }

    @PatchMapping("/verify")
    public ResponseEntity<AuthenticationResponse> verifyUser(@RequestParam String emailId, @RequestParam String otp) {
        return userService.verifyUser(emailId, otp);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest data) {
        return userService.login(data);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return userService.refreshToken(request, response);
    }

    @PatchMapping("/forget-password")
    public ResponseEntity<AuthenticationResponse> forgetPassword(@RequestParam String emailId) {
        return userService.forgotPassword(emailId);
    }

    @PatchMapping("/forget-password-verify")
    public ResponseEntity<AuthenticationResponse> verifyOtpForgetPassword(@RequestParam String emailId,
                                                                          @RequestParam String otp,
                                                                          @RequestParam String password) {
        return userService.verifyForgetPassword(emailId, otp, password);
    }
}
