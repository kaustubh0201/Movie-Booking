package com.project.moviebooking.exception;

/**
 * Custom exception indicating that booked seats information was not found.
 * Extends the Exception class to handle specific cases of booked seats not being found.
 */
public class BookedSeatsNotFoundException extends Exception {

    /**
     * Constructs a BookedSeatsNotFoundException with a specific message and a cause.
     * @param message The detail message explaining the exception.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public BookedSeatsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a BookedSeatsNotFoundException with a specific message.
     * @param message The detail message explaining the exception.
     */
    public BookedSeatsNotFoundException(String message) {
        super(message);
    }
}
