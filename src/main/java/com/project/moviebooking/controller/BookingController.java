package com.project.moviebooking.controller;

import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.service.impl.BookingServiceImpl;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @Autowired
    private Utils utils;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody @NotNull BookingRequest bookingRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(bookingRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Booking.builder().build());
        }

    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookingsByUsername(@RequestParam (defaultValue = "0") int page,
                                                                          @RequestParam (defaultValue = "10") int size) {

        String username = utils.getUsername();

        if (username.equals(Constants.EMPTY_STRING)) {
            log.error("Username not found in the context");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
        }

        try {
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(bookingService.getAllBookingsByUsername(username, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
