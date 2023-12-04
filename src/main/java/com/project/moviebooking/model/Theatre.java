package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Represents the model for storing information about a theatre.
 */
@Document("theatre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {

    /**
     * Represents the unique identifier of the theatre.
     */
    @Id
    private String theatreId;

    /**
     * Represents the name of the theatre.
     */
    private String theatreName;

    /**
     * Represents a mapping of auditorium numbers to the number of seats in each auditorium.
     */
    private Map<Integer, Integer> auditoriumNumberToNumberOfSeats;

    /**
     * Represents the address of the theatre.
     */
    private String theatreAddress;

    /**
     * Represents the city where the theatre is located.
     */
    private String theatreCity;

    /**
     * Represents the state where the theatre is located.
     */
    private String theatreState;
}
