package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing a request for booking seats for a show.
 * Contains fields for the show ID and a list of booked seat numbers.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    /**
     * Represents the ID of the show for which seats are being booked.
     */
    private String showId;

    /**
     * Represents a list of integers indicating the seat numbers being booked.
     */
    private List<Integer> bookedSeats;
}
