package com.project.moviebooking.repository;

import com.project.moviebooking.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing operations related to movies in the database.
 * Extends Spring Data's MongoRepository for Movie entities.
 */
public interface MovieRepository extends MongoRepository<Movie, String> {

    /**
     * Retrieves a list of movies based on the provided movie name.
     *
     * @param movieName The name of the movie to retrieve.
     * @return An Optional containing a list of movies with the provided name, if found.
     */
    Optional<List<Movie>> findByMovieName(String movieName);

    /**
     * Retrieves a page of movies based on the provided movie name.
     *
     * @param movieName The name of the movie to retrieve.
     * @param pageable  Pagination information for the results.
     * @return A Page containing movies with the provided name.
     */
    Page<Movie> findByMovieName(String movieName, Pageable pageable);
}
