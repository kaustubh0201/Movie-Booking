package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Represents the model for storing information about a movie.
 */
@Document("movie")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    /**
     * Represents the unique identifier of the movie.
     */
    @Id
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
