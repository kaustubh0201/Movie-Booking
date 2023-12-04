package com.project.moviebooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a response after user authentication.
 * Contains fields for a message, access token, and refresh token.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {

    /**
     * Represents the message or status provided in the authentication response.
     */
    private String message;

    /**
     * Represents the access token issued after successful authentication.
     */
    private String accessToken;

    /**
     * Represents the refresh token used for token refresh operations.
     */
    private String refreshToken;
}
