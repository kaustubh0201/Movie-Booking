package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents the model for storing information about booked seats for a show.
 */
@Document("booked_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookedSeats {

    /**
     * Represents the unique identifier of the show for which seats are booked.
     */
    @Id
    private String showId;

    /**
     * Represents a list of integers denoting all booked seats for the show.
     */
    private List<Integer> allBookedSeats;
}
