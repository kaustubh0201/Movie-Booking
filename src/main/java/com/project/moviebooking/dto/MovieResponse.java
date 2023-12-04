package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a response after retrieving movie details.
 * Contains fields for the movie ID, name, release date, and duration.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse implements Serializable {

    /**
     * Represents the unique identifier for the movie.
     */
    private String movieId;

    /**
     * Represents the name of the movie.
     */
    private String movieName;

    /**
     * Represents the release date of the movie.
     */
    private Date releaseDate;

    /**
     * Represents the duration of the movie in minutes.
     */
    private Integer movieDuration;
}
