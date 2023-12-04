package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a request to schedule a show.
 * Contains fields for the movie ID, theatre ID, auditorium number, and show time.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequest {

    /**
     * Represents the unique identifier of the movie for which the show is scheduled.
     */
    private String movieId;

    /**
     * Represents the unique identifier of the theatre where the show will take place.
     */
    private String theatreId;

    /**
     * Represents the number of the auditorium where the show will be held.
     */
    private Integer auditorium;

    /**
     * Represents the time at which the show will start.
     */
    private Date showTime;
}
