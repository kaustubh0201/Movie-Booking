package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a request for user authentication.
 * Contains fields for username and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**
     * Represents the username provided in the authentication request.
     */
    public String username;

    /**
     * Represents the password provided in the authentication request.
     */
    public String password;
}
