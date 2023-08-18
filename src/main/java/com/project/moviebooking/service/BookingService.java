package com.project.moviebooking.service;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void createBooking(BookingRequest bookingRequest) {

        String username = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();

        Booking booking = Booking.builder()
                .showId(bookingRequest.getShowId())
                .bookedSeats(bookingRequest.getBookedSeats())
                .username(username)
                .build();

        bookingRepository.save(booking);
        log.info("Booking added to the database with {}", booking.getBookingId());

    }

    public List<BookingResponse> getAllBookingsByUsername(String username) {

        Optional<List<Booking>> bookings = bookingRepository.findByUsername(username);

        return null;

    }

}
