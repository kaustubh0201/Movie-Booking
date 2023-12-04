package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * Data Transfer Object (DTO) representing a response after retrieving theatre details.
 * Contains fields for the theatre ID, name, mapping of auditorium numbers to seat counts,
 * theatre address, city, and state.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreResponse implements Serializable {

    /**
     * Represents the unique identifier for the theatre.
     */
    private String theatreId;

    /**
     * Represents the name of the theatre.
     */
    private String theatreName;

    /**
     * Represents a mapping of auditorium numbers to their respective seat counts in the theatre.
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


