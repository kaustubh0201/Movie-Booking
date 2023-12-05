package com.project.moviebooking.exception;

/**
 * Custom exception indicating that user is not verified.
 * Extends the Exception class to handle specific cases of user being not verified.
 */
public class UserNotVerifiedException extends Exception {

    /**
     * Constructs a UserNotVerifiedException with a specific message and a cause.
     * @param message The detail message explaining the exception.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public UserNotVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a UserNotVerifiedException with a specific message.
     * @param message The detail message explaining the exception.
     */
    public UserNotVerifiedException(String message) {
        super(message);
    }
}
