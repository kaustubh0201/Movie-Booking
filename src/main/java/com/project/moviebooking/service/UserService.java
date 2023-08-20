package com.project.moviebooking.service;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.dto.UserDTO;
import com.project.moviebooking.model.User;
import com.project.moviebooking.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<AuthenticationResponse> createUser(UserDTO userDTO) {
        if(userRepository.findByEmailId(userDTO.getEmailId()).isPresent()) {
            throw new DuplicateKeyException("User with given details already exists." +
                    " Please try forget password to recover the account");
        }

        AuthenticationResponse response;

        try {
            String otp = generateOTP();
            User user = User.builder()
                    .userId(UUID.randomUUID().toString())
                    .name(userDTO.getName())
                    .username(userDTO.getUsername())
                    .emailId(userDTO.getEmailId())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .isVerified(false)
                    .otp(otp)
                    .build();

            sendOTP(userDTO.getEmailId(), otp);
            userRepository.save(user);

            response = AuthenticationResponse.builder()
                    .message("User has been registered in the database and" +
                            " OTP is sent for verification")
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DuplicateKeyException e) {
            response = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<AuthenticationResponse> verifyUser(String emailId, String otp) {
        AuthenticationResponse response;
        try {
            User user = userRepository.findByEmailId(emailId).orElseThrow(() ->
                    new NullPointerException("The given user is not found")
            );

            if(user.getOtp().equals(otp)) {
                user.setVerified(true);
                userRepository.save(user);

                response = AuthenticationResponse.builder()
                        .message("User has been verified")
                        .build();
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            response = AuthenticationResponse.builder()
                    .message("Wrong OTP has been entered")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (NullPointerException e) {
            response = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<AuthenticationResponse> loginUser(AuthenticationRequest credentials) {
        AuthenticationResponse response;
        try {
            String username = credentials.getUsername();
            String password = credentials.getPassword();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );

            User user = userRepository.findByUsername(username).orElseThrow(() ->
                    new NullPointerException("Wrong credentials entered")
            );

            if(user.isVerified()) {
                String jwtToken = jwtService.generateToken(user);
                String refreshToken = jwtService.generateRefreshToken(user);
                response = AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .message("Login Successful!")
                        .build();
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response = AuthenticationResponse.builder()
                    .message("User is not verified")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (IllegalArgumentException e) {
            response = AuthenticationResponse.builder()
                    .message("Unable to get JWT Token")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (ExpiredJwtException e) {
            response = AuthenticationResponse.builder()
                    .message("JWT token has expired")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            response = AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        AuthenticationResponse authResponse;

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            authResponse = AuthenticationResponse.builder()
                    .message("Authorization header not found")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);

        if(username != null) {
            UserDetails userDetails = userRepository.findByUsername(username)
                    .orElseThrow();
            if(jwtService.isTokenValid(refreshToken, userDetails)) {
                String accessToken = jwtService.generateToken(userDetails);
                authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .message("Access token refreshed")
                        .build();

                return ResponseEntity.status(HttpStatus.OK).body(authResponse);
            }
        }
        authResponse = AuthenticationResponse.builder()
                .message("Access token is not refreshed")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(authResponse);
    }

    private String generateOTP() {
        String otpCharacters = "0123456789";
        Random random = new Random();

        return IntStream.range(0, 4)
                .mapToObj(i -> otpCharacters.charAt(random.nextInt(otpCharacters.length())))
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void sendOTP(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);
        mailMessage.setSubject("Code-Sync OTP Verification");
        mailMessage.setText(String.format("""
                Hello!

                Thank you for signing up with Movie-Booking.
                Here's the OTP for your account verification: %s

                Regards,
                Team Movie-Booking""", otp));

        javaMailSender.send(mailMessage);
    }

}
