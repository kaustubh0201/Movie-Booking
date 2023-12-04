package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents the model for storing information about a booking.
 */
@Document("booking")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    /**
     * Represents the unique identifier of the booking.
     */
    @Id
    private String bookingId;

    /**
     * Represents the unique identifier of the show for which the booking is made.
     */
    private String showId;

    /**
     * Represents the username of the user who made the booking.
     */
    private String username;

    /**
     * Represents a list of integers denoting the seats that are booked for the show.
     */
    private List<Integer> bookedSeats;
}
