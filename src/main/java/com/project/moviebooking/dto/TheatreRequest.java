package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Data Transfer Object (DTO) representing a request to add a new theatre.
 * Contains fields for the theatre name, mapping of auditorium numbers to seat counts,
 * theatre address, city, and state.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreRequest {

    /**
     * Represents the name of the theatre being added.
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
