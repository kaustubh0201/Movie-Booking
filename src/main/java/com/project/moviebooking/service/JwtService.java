package com.project.moviebooking.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * Service interface defining operations related to JWT (JSON Web Token) handling.
 * Handles token generation, resolution, validation, and extraction of user details.
 */
public interface JwtService {

    /**
     * Generates an access token for the provided username and roles.
     *
     * @param username The username for whom the access token is generated.
     * @param roles    The roles associated with the user.
     * @return The generated access token.
     */
    String generateAccessToken(String username, List<String> roles);

    /**
     * Generates a refresh token for the provided username and roles.
     *
     * @param username The username for whom the refresh token is generated.
     * @param roles    The roles associated with the user.
     * @return The generated refresh token.
     */
    String generateRefreshToken(String username, List<String> roles);

    /**
     * Resolves the token from the HTTPServletRequest.
     *
     * @param request The HTTPServletRequest containing the token.
     * @return The resolved token.
     */
    String resolveToken(HttpServletRequest request);

    /**
     * Retrieves the authentication object from the provided token.
     *
     * @param token The token used for authentication.
     * @return The authentication object.
     */
    Authentication getAuthentication(String token);

    /**
     * Checks if the provided token is valid for the given user.
     *
     * @param token The token to validate.
     * @param user  The user for whom the token is being validated.
     * @return True if the token is valid, false otherwise.
     */
    boolean isTokenValid(String token, String user);

    /**
     * Checks if the provided token has expired.
     *
     * @param token The token to check for expiration.
     * @return True if the token has expired, false otherwise.
     */
    boolean isTokenExpired(String token);

    /**
     * Extracts the username from the provided token.
     *
     * @param token The token from which to extract the username.
     * @return The extracted username.
     */
    String extractUsername(String token);
}