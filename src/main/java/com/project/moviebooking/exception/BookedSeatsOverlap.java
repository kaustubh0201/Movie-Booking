package com.project.moviebooking.exception;

/**
 * Custom exception indicating that booked seats overlap.
 * Extends the Exception class to handle specific cases of seat overlapping in bookings.
 */
public class BookedSeatsOverlap extends Exception{

    /**
     * Constructs a BookedSeatsOverlap with a specific message and a cause.
     * @param message The detail message explaining the exception.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public BookedSeatsOverlap(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a BookedSeatsOverlap with a specific message.
     * @param message The detail message explaining the exception.
     */
    public BookedSeatsOverlap(String message) {
        super(message);
    }
}
