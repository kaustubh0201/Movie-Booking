package com.project.moviebooking.repository;

import com.project.moviebooking.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing operations related to bookings in the database.
 * Extends Spring Data's MongoRepository for Booking entities.
 */
public interface BookingRepository extends MongoRepository<Booking, String> {

    /**
     * Retrieves a list of bookings based on the provided username.
     *
     * @param username The username associated with the bookings to retrieve.
     * @return An Optional containing a list of bookings for the given username, if found.
     */
    Optional<List<Booking>> findByUsername(String username);

    /**
     * Retrieves a page of bookings based on the provided username.
     *
     * @param username The username associated with the bookings to retrieve.
     * @param pageable Pagination information for the results.
     * @return A Page containing bookings for the given username.
     */
    Page<Booking> findByUsername(String username, Pageable pageable);
}
