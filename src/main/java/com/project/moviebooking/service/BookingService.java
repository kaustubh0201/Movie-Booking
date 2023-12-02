package com.project.moviebooking.service;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.exception.BookedSeatsNotFoundException;
import com.project.moviebooking.exception.BookedSeatsOverlap;
import com.project.moviebooking.model.Booking;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequest bookingRequest) throws BookedSeatsNotFoundException, BookedSeatsOverlap;
    List<BookingResponse> getAllBookingsByUsername(String username);
    List<BookingResponse> getAllBookingsByUsername(String username, Pageable pageable);
}
