package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Represents the model for storing information about a show.
 */
@Document("show")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    /**
     * Represents the unique identifier of the show.
     */
    @Id
    private String showId;

    /**
     * Represents the unique identifier of the movie associated with the show.
     */
    private String movieId;

    /**
     * Represents the unique identifier of the theatre where the show is hosted.
     */
    private String theatreId;

    /**
     * Represents the auditorium number where the show is scheduled.
     */
    private Integer auditorium;

    /**
     * Represents the time at which the show starts.
     */
    private Date showTime;
}
