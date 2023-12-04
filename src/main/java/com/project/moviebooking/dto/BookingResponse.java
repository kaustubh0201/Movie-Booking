package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing a response after successful booking of seats for a show.
 * Contains fields for a booking ID, details of the booked show, and a list of booked seat numbers.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse implements Serializable {

    /**
     * Represents the unique identifier for the booking.
     */
    private String bookingId;

    /**
     * Represents the details of the show that has been booked.
     */
    private ShowResponse showResponse;

    /**
     * Represents a list of integers indicating the seat numbers that have been booked.
     */
    private List<Integer> bookedSeats;
}
