package com.project.moviebooking.controller;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.UserDTO;
import com.project.moviebooking.service.UserService;
import com.project.moviebooking.dto.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    public ResponseEntity<AuthenticationResponse> verifyUser(@RequestParam @Valid String emailId,
                                                             @RequestParam @Valid String otp) {
        return userService.verifyUser(emailId, otp);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody @Valid AuthenticationRequest credentials) {
        return userService.loginUser(credentials);
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request,
                                                               HttpServletResponse response) {
        return userService.refreshToken(request, response);
    }

}
