package com.project.moviebooking.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception indicating an invalid JWT authentication.
 * Extends the AuthenticationException class to handle specific cases of JWT authentication failure.
 */
public class InvalidJwtAuthenticationException extends AuthenticationException {

    /**
     * Constructs an InvalidJwtAuthenticationException with a specific message and a cause.
     * @param message The detail message explaining the exception.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public InvalidJwtAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an InvalidJwtAuthenticationException with a specific message.
     * @param message The detail message explaining the exception.
     */
    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
