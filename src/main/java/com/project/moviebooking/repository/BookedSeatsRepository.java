package com.project.moviebooking.repository;

import com.project.moviebooking.model.BookedSeats;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for managing operations related to booked seats in the database.
 * Extends Spring Data's MongoRepository for BookedSeats entities.
 */
public interface BookedSeatsRepository extends MongoRepository<BookedSeats, String> {

}
