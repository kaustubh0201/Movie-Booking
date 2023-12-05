package com.project.moviebooking.controller;

import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.exception.BookedSeatsNotFoundException;
import com.project.moviebooking.exception.BookedSeatsOverlapException;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.service.impl.BookingServiceImpl;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a controller class for the booking of the seats.
 * The API requests for booking are declared here.
 * The methods to create a booking, get the user's bookings and other related activities are present here.
 */
@RestController
@Slf4j
@RequestMapping("/api/booking")
public class BookingController {

    /**
     * This object is for accessing the functions of the booking service layer.
     */
    @Autowired
    private BookingServiceImpl bookingService;

    /**
     * This object is used for access the different utility functions present in the utility class.
     */
    @Autowired
    private Utils utils;

    /**
     * This method is for the creation of booking which can be done by the users and admins.
     * @param bookingRequest Contains the information of the booking that needs to be created.
     * @return A created booking body is returned if the booking is created successfully along with message otherwise
     * in the case of error only error message is returned.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createBooking(@RequestBody @NotNull BookingRequest bookingRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            Booking booking = bookingService.createBooking(bookingRequest);
            response.put("booking", booking);
            response.put("message", "Booking done successfully");
            log.info("Booking has been done successfully with bookingId: {}", booking.getBookingId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (BookedSeatsNotFoundException | BookedSeatsOverlapException e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while booking seats with error: {} and showId: {}", e.getMessage(),
                    bookingRequest.getShowId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while booking seats with error: {} and showId: {}", e.getMessage(),
                    bookingRequest.getShowId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * This method is used to get the bookings by the username. The username is fetched from the Security Context Holder.
     * @param page This is the page number for the pagination purpose.
     * @param size This is the size of each page the pagination purpose.
     * @return A list with all the booking information is returned along with a message otherwise in the case of any error
     * only the error message is returned.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBookingsByUsername(@RequestParam (defaultValue = "0") int page,
                                                                          @RequestParam (defaultValue = "10") int size) {

        String username = utils.getUsername();
        Map<String, Object> response = new HashMap<>();

        if (username.equals(Constants.EMPTY_STRING)) {
            log.error("Username not found in the authorization context while fetching Bookings.");
            response.put("errorMessage",
                    "Username not found in the authorization context. Please try logging in again!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Pageable pageable = PageRequest.of(page, size);
            response.put("bookingList", bookingService.getAllBookingsByUsername(username, pageable));
            response.put("message", "Successfully got all the booking by the username: " + username);
            log.info("Successfully fetched all the bookings for the user: {}", username);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while fetching bookings for username: {} with error: {}", username, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
