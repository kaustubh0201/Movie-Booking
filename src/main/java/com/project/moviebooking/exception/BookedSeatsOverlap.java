package com.project.moviebooking.exception;

public class BookedSeatsOverlap extends Exception{

    public BookedSeatsOverlap(String message, Throwable cause) {
        super(message, cause);
    }

    public BookedSeatsOverlap(String message) {
        super(message);
    }
}
