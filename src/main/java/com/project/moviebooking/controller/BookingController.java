package com.project.moviebooking.controller;

import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.exception.BookedSeatsNotFoundException;
import com.project.moviebooking.exception.BookedSeatsOverlap;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @Autowired
    private Utils utils;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBooking(@RequestBody @NotNull BookingRequest bookingRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("booking", bookingService.createBooking(bookingRequest));
            response.put("message", "Booking done successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (BookedSeatsNotFoundException | BookedSeatsOverlap e) {
            response.put("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
