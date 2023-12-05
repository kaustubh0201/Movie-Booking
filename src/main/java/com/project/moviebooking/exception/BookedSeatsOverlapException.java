package com.project.moviebooking.exception;

/**
 * Custom exception indicating that booked seats overlap.
 * Extends the Exception class to handle specific cases of seat overlapping in bookings.
 */
public class BookedSeatsOverlapException extends Exception {

    /**
     * Constructs a BookedSeatsOverlapException with a specific message and a cause.
     * @param message The detail message explaining the exception.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public BookedSeatsOverlapException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a BookedSeatsOverlapException with a specific message.
     * @param message The detail message explaining the exception.
     */
    public BookedSeatsOverlapException(String message) {
        super(message);
    }
}
