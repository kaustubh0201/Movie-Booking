package com.project.moviebooking.service.impl;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.exception.BookedSeatsNotFoundException;
import com.project.moviebooking.exception.BookedSeatsOverlap;
import com.project.moviebooking.model.BookedSeats;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.repository.BookedSeatsRepository;
import com.project.moviebooking.repository.BookingRepository;
import com.project.moviebooking.service.BookingService;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the BookingService interface, handling booking-related operations.
 */
@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository; // Repository handling Booking entities.

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository; // Repository handling BookedSeats entities.

    @Autowired
    private ShowServiceImpl showService; // Service handling Show entities.

    @Autowired
    private Utils utils; // Utility class for transformation and mapping operations.

    /**
     * Creates a new booking based on the provided booking request.
     *
     * @param bookingRequest The request containing details to create a booking.
     * @return The created booking entity.
     * @throws BookedSeatsNotFoundException If the booked seats entity is not found for the show ID.
     * @throws BookedSeatsOverlap           If the seats being booked are already booked.
     */
    @Override
    public Booking createBooking(BookingRequest bookingRequest)
            throws BookedSeatsNotFoundException, BookedSeatsOverlap {
        Booking booking = utils.bookingRequestToBooking(bookingRequest);

        BookedSeats bookedSeats = bookedSeatsRepository.findById(booking.getShowId()).orElseThrow(() ->
                new BookedSeatsNotFoundException("No booked seats entity found for showId: " +
                        bookingRequest.getShowId()));

        boolean hasCommon = bookedSeats.getAllBookedSeats().stream()
                .anyMatch(bookingRequest.getBookedSeats()::contains);

        if (hasCommon) {
            throw new BookedSeatsOverlap("Seats trying to book are already booked!");
        }

        bookedSeats.getAllBookedSeats().addAll(bookingRequest.getBookedSeats());
        bookedSeatsRepository.save(bookedSeats);

        booking = bookingRepository.save(booking);
        log.info("Booking added to the database with {}", booking.getBookingId());

        return booking;
    }

    /**
     * Retrieves all bookings for a specific username.
     *
     * @param username The username for which bookings are to be retrieved.
     * @return A list of booking responses associated with the provided username.
     */
    @Override
    @Cacheable(value = "bookingCache", key = "#username")
    public List<BookingResponse> getAllBookingsByUsername(String username) {
        Optional<List<Booking>> bookings = bookingRepository.findByUsername(username);

        return bookings.map(bookingList -> bookingList.stream()
                        .map(booking -> utils.bookingToBookingResponseTransformer(
                                booking,
                                showService.getShowByShowId(booking.getShowId())
                        ))
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }

    /**
     * Retrieves all bookings for a specific username with pagination.
     *
     * @param username The username for which bookings are to be retrieved.
     * @param pageable The pagination information.
     * @return A list of paginated booking responses associated with the provided username.
     */
    @Override
    @Cacheable(value = "bookingCache",
            key = "#username + '_' + #pageable.getPageNumber() + '_' + #pageable.getPageSize()")
    public List<BookingResponse> getAllBookingsByUsername(String username, Pageable pageable) {
        List<Booking> bookings = bookingRepository.findByUsername(username, pageable).getContent();

        return bookings.stream().map(booking -> utils.bookingToBookingResponseTransformer(
                booking,
                showService.getShowByShowId(booking.getShowId())
        )).collect(Collectors.toList());
    }
}
