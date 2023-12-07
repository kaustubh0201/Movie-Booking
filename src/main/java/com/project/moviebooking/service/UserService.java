package com.project.moviebooking.service;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.dto.UserRequest;
import com.project.moviebooking.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

/**
 * Service interface responsible for user-related operations in the movie booking system.
 * Provides methods for user creation, login, verification, token handling, and password management.
 */
public interface UserService {

    /**
     * Creates a new user based on the provided user details.
     *
     * @param userRequest The request containing details to create a user.
     * @return The response entity with authentication details for the created user.
     */
    ResponseEntity<AuthenticationResponse> createUser(UserRequest userRequest);

    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param request The authentication request containing user credentials.
     * @return The response entity with authentication details upon successful login.
     */
    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request);

    /**
     * Verifies a user based on the provided email ID and OTP.
     *
     * @param emailId The email ID of the user to be verified.
     * @param otp     The one-time password for user verification.
     * @return The response entity with authentication details upon successful user verification.
     */
    ResponseEntity<AuthenticationResponse> verifyUser(String emailId, String otp);

    /**
     * Refreshes the authentication token for a user.
     *
     * @param request  The HTTP servlet request.
     * @param response The HTTP servlet response.
     * @return The response entity with a refreshed authentication token.
     */
    ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * Initiates the password reset process for a user based on the provided email ID.
     *
     * @param emailId The email ID for which password reset is requested.
     * @return The response entity with details regarding the password reset process.
     */
    ResponseEntity<AuthenticationResponse> forgotPassword(String emailId);

    /**
     * Verifies the password reset request using the provided email ID, OTP, and new password.
     *
     * @param emailId  The email ID for which password reset is requested.
     * @param otp      The one-time password for password reset verification.
     * @param password The new password for the user account.
     * @return The response entity confirming successful password reset.
     */
    ResponseEntity<AuthenticationResponse> verifyForgetPassword(String emailId, String otp, String password);

    /**
     * Sends an OTP again for a specific email ID as part of the forget password process.
     *
     * @param emailId The email ID for which OTP resend is requested.
     * @return The response entity with details regarding the OTP resend process.
     */
    ResponseEntity<AuthenticationResponse> sendOtpAgain(String emailId);

    /**
     * Sends the current logged-in user information.
     *
     * @param username The username for which the information is fetched.
     * @return The response entity with details regarding the user info is returned.
     */
    User getCurrentUser(String username);
}