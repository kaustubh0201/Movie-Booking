package com.project.moviebooking.service.impl;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.repository.BookingRepository;
import com.project.moviebooking.service.BookingService;
import com.project.moviebooking.util.Utils;
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
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ShowServiceImpl showService;

    @Autowired
    private Utils utils;

    @Override
    public void createBooking(BookingRequest bookingRequest) {
        Booking booking = utils.bookingRequestToBooking(bookingRequest);

        bookingRepository.save(booking);
        log.info("Booking added to the database with {}", booking.getBookingId());
    }

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
}
