package com.project.moviebooking.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Utility class responsible for generating OTPs (One-Time Passwords).
 */
@Component
public class OtpGenerator {

    // The characters used to create the OTP.
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // The length of the generated OTP.
    private static final int OTP_LENGTH = 6;

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