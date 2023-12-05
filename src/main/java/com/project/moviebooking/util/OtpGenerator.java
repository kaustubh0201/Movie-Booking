package com.project.moviebooking.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

import static com.project.moviebooking.constant.Constants.CHARACTERS;
import static com.project.moviebooking.constant.Constants.OTP_LENGTH;

/**
 * Utility class responsible for generating OTPs (One-Time Passwords).
 */
@Component
public class OtpGenerator {

    /**
     * Generates a random OTP of a specified length.
     *
     * @return A randomly generated OTP string.
     */
    public String generateOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            otp.append(CHARACTERS.charAt(randomIndex));
        }

        return otp.toString();
    }
}