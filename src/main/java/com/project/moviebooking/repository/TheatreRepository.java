package com.project.moviebooking.repository;

import com.project.moviebooking.model.Theatre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing operations related to theatres in the database.
 * Extends Spring Data's MongoRepository for Theatre entities.
 */
public interface TheatreRepository extends MongoRepository<Theatre, String> {

    /**
     * Retrieves a list of theatres based on the provided city and theatre name.
     *
     * @param theatreCity  The city in which the theatre is located.
     * @param theatreName  The name of the theatre.
     * @return An Optional containing a list of theatres with the provided city and name, if found.
     */
    Optional<List<Theatre>> findByTheatreCityAndTheatreName(String theatreCity, String theatreName);

    /**
     * Retrieves a list of theatres based on the provided city.
     *
     * @param theatreCity  The city in which the theatres are located.
     * @return An Optional containing a list of theatres within the provided city, if found.
     */
    Optional<List<Theatre>> findByTheatreCity(String theatreCity);

    /**
     * Retrieves a page of theatres based on the provided city and theatre name.
     *
     * @param theatreCity  The city in which the theatre is located.
     * @param theatreName  The name of the theatre.
     * @param pageable     Pagination information for the results.
     * @return A Page containing theatres with the provided city and name.
     */
    Page<Theatre> findByTheatreCityAndTheatreName(String theatreCity, String theatreName, Pageable pageable);

    /**
     * Retrieves a page of theatres based on the provided city.
     *
     * @param theatreCity  The city in which the theatres are located.
     * @param pageable     Pagination information for the results.
     * @return A Page containing theatres within the provided city.
     */
    Page<Theatre> findByTheatreCity(String theatreCity, Pageable pageable);
}
