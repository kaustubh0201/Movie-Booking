package com.project.moviebooking.service;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.dto.UserRequest;
import com.project.moviebooking.model.User;
import com.project.moviebooking.repository.UserRepository;
import com.project.moviebooking.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private Utils utils;

    public ResponseEntity<AuthenticationResponse> createUser(UserRequest userRequest) {

        AuthenticationResponse authenticationResponse;
        final User user = utils.userRequestToUserTransformer(userRequest);

        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new DuplicateKeyException("User with given details already exists."
                        + " Please try forget password to recover the account");
            }

            userRepository.save(user);

            authenticationResponse = AuthenticationResponse.builder()
                    .message("User has been successfully registered.")
                    .build();

            log.info("User added to the database with {}", user.getUserId());
            return ResponseEntity.ok(authenticationResponse);
        } catch (DuplicateKeyException e) {
            authenticationResponse = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();

            log.error("User with username: " + user.getUsername() + " already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticationResponse);
        } catch (Exception e) {
            authenticationResponse = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();

            log.error("Error occurred for username: {} {}", user.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authenticationResponse);
        }
    }

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {

        AuthenticationResponse response;
        final String username = request.getUsername();
        final String password = request.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            List<String> roles = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + "not found!"))
                    .getRoles();

            String accessToken = jwtService.generateAccessToken(username, roles);
            String refreshToken = jwtService.generateRefreshToken(username, roles);

            response = AuthenticationResponse.builder()
                    .message("User " + username + " successfully logged in!")
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            response = AuthenticationResponse.builder()
                    .message("Username: " + username + " was not found!")
                    .build();

            log.info("Username: {} was not found!", username);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();

            log.info("Error occurred while logging with username: {}", username);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        AuthenticationResponse authenticationResponse;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            authenticationResponse = AuthenticationResponse.builder()
                    .message("Authorization header not found!")
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticationResponse);
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            try {
                List<String> roles = userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + "not found!"))
                        .getRoles();

                if (jwtService.isTokenValid(refreshToken, username)) {

                    String accessToken = jwtService.generateAccessToken(username, roles);
                    authenticationResponse = AuthenticationResponse.builder()
                            .message("Access token has been refreshed!")
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();

                    return ResponseEntity.ok(authenticationResponse);

                }

            } catch (UsernameNotFoundException e) {
                authenticationResponse = AuthenticationResponse.builder()
                        .message("Username not found! " + e.getMessage())
                        .build();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authenticationResponse);
            }
        }

        authenticationResponse = AuthenticationResponse.builder()
                .message("Access token has not been refreshed!")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authenticationResponse);
    }
}
