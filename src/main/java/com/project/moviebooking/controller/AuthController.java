package com.project.moviebooking.controller;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.repository.UserRepository;
import com.project.moviebooking.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                    "Username: " + username + "not found!"
            )).getRoles();

            String accessToken = jwtService.generateAccessToken(username, roles);
            String refreshToken = jwtService.generateRefreshToken(username, roles);

            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .message("Token created for username " + username)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build());

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
