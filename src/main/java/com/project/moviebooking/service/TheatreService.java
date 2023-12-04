package com.project.moviebooking.service;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface responsible for managing theaters in the movie booking system.
 * Offers methods for creating, retrieving, and querying theaters based on different criteria.
 */
public interface TheatreService {

    /**
     * Creates a new theater based on the provided theater details.
     *
     * @param theatreRequest The request containing details to create a theater.
     * @return The created theater.
     */
    TheatreResponse createTheatre(TheatreRequest theatreRequest);

    /**
     * Retrieves a paginated list of theaters in a specific city.
     *
     * @param theatreCity The city for which theaters need to be retrieved.
     * @param pageable    Pagination information.
     * @return A paginated list of theater responses for the provided city.
     */
    List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity, Pageable pageable);

    /**
     * Retrieves a list of theaters in a specific city.
     *
     * @param theatreCity The city for which theaters need to be retrieved.
     * @return A list of theater responses for the provided city.
     */
    List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity);

    /**
     * Retrieves a paginated list of theaters by city and theater name.
     *
     * @param theatreCity  The city for which theaters need to be retrieved.
     * @param theatreName  The name of the theater being searched.
     * @param pageable     Pagination information.
     * @return A paginated list of theater responses for the provided city and theater name.
     */
    List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity,
                                                                   String theatreName, Pageable pageable);

    /**
     * Retrieves a list of theaters by city and theater name.
     *
     * @param theatreCity The city for which theaters need to be retrieved.
     * @param theatreName The name of the theater being searched.
     * @return A list of theater responses for the provided city and theater name.
     */
    List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity, String theatreName);

    /**
     * Retrieves a specific theater based on the theater ID.
     *
     * @param theatreId The ID of the theater to retrieve.
     * @return The theater response corresponding to the provided theater ID.
     */
    TheatreResponse getTheatreByTheatreId(String theatreId);
}