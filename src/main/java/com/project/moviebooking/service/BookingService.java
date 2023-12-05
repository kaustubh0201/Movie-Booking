package com.project.moviebooking.service;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.exception.BookedSeatsNotFoundException;
import com.project.moviebooking.exception.BookedSeatsOverlapException;
import com.project.moviebooking.model.Booking;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface defining operations related to bookings within the movie booking system.
 */
public interface BookingService {

    /**
     * Creates a booking based on the provided booking request.
     *
     * @param bookingRequest The details of the booking to be created.
     * @return The created booking.
     * @throws BookedSeatsNotFoundException If booked seats are not found.
     * @throws BookedSeatsOverlapException          If booked seats overlap.
     */
    Booking createBooking(BookingRequest bookingRequest) throws BookedSeatsNotFoundException, BookedSeatsOverlapException;

    /**
     * Retrieves all bookings for a specific username.
     *
     * @param username The username for which bookings are to be retrieved.
     * @return A list of booking responses associated with the provided username.
     */
    List<BookingResponse> getAllBookingsByUsername(String username);

    /**
     * Retrieves paginated bookings for a specific username.
     *
     * @param username The username for which bookings are to be retrieved.
     * @param pageable Pagination information.
     * @return A list of paginated booking responses associated with the provided username.
     */
    List<BookingResponse> getAllBookingsByUsername(String username, Pageable pageable);
}
