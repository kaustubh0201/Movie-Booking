package com.project.moviebooking.dto;

import com.project.moviebooking.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a response after retrieving show details.
 * Contains fields for the show ID, details of the movie, theatre, auditorium, and show time.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponse implements Serializable {

    /**
     * Represents the unique identifier for the show.
     */
    private String showId;

    /**
     * Represents details about the movie for this show.
     */
    private MovieResponse movieResponse;

    /**
     * Represents details about the theatre where the show takes place.
     */
    private TheatreResponse theatreResponse;

    /**
     * Represents the number of the auditorium for the show.
     */
    private Integer auditorium;

    /**
     * Represents the time at which the show starts.
     */
    private Date showTime;
}
