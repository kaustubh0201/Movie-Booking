package com.project.moviebooking.service;

import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.dto.UserDTO;
import com.project.moviebooking.model.User;
import com.project.moviebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
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
