package com.project.moviebooking.service;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.repository.BookingRepository;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowService showService;

    @Autowired
    private Utils utils;

    public void createBooking(BookingRequest bookingRequest) {

        Booking booking = utils.bookingRequestToBooking(bookingRequest);

        bookingRepository.save(booking);
        log.info("Booking added to the database with {}", booking.getBookingId());

    }

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
}
