package com.project.moviebooking.service;

import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void createBooking(BookingRequest bookingRequest) {

        // TODO: Write the code for building the Booking
        Booking booking = Booking.builder().build();

        bookingRepository.save(booking);
        log.info("Booking added to the database with {}", booking.getBookingId());

    }

}
