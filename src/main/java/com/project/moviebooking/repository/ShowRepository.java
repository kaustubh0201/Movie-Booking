package com.project.moviebooking.repository;

import com.project.moviebooking.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing operations related to shows in the database.
 * Extends Spring Data's MongoRepository for Show entities.
 */
public interface ShowRepository extends MongoRepository<Show, String> {

    /**
     * Retrieves a list of shows based on the provided movie ID.
     *
     * @param movieId The ID of the movie associated with the shows to retrieve.
     * @return An Optional containing a list of shows for the given movie ID, if found.
     */
    Optional<List<Show>> findByMovieId(String movieId);

    /**
     * Retrieves a list of shows based on the provided movie ID and theatre ID.
     *
     * @param movieId   The ID of the movie associated with the shows to retrieve.
     * @param theatreId The ID of the theatre associated with the shows to retrieve.
     * @return An Optional containing a list of shows for the given movie ID and theatre ID, if found.
     */
    Optional<List<Show>> findByMovieIdAndTheatreId(String movieId, String theatreId);

    /**
     * Retrieves a show based on the provided show ID.
     *
     * @param showId The ID of the show to retrieve.
     * @return An Optional containing the show with the given ID, if found.
     */
    Optional<Show> findByShowId(String showId);

}
