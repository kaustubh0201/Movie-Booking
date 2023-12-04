package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a request for adding a new movie.
 * Contains fields for the movie name, release date, exit date, and movie duration.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    /**
     * Represents the name of the movie being added.
     */
    private String movieName;

    /**
     * Represents the release date of the movie.
     */
    private Date releaseDate;

    /**
     * Represents the exit date or end date of the movie from the theaters.
     */
    private Date exitDate;

    /**
     * Represents the duration of the movie in minutes.
     */
    private Integer movieDuration;
}
