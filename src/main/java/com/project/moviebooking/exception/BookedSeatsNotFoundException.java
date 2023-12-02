package com.project.moviebooking.exception;

import com.project.moviebooking.model.BookedSeats;

public class BookedSeatsNotFoundException extends Exception {

    public BookedSeatsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookedSeatsNotFoundException(String message) {
        super(message);
    }
}
