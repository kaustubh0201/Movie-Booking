package com.project.moviebooking.service;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    void createBooking(BookingRequest bookingRequest);
    List<BookingResponse> getAllBookingsByUsername(String username);
}
