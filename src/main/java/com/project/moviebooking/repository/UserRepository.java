package com.project.moviebooking.repository;

import com.project.moviebooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository interface for managing operations related to users in the database.
 * Extends Spring Data's MongoRepository for User entities.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Retrieves a user based on the provided email ID.
     *
     * @param emailId The email ID of the user to retrieve.
     * @return An Optional containing the user with the provided email ID, if found.
     */
    Optional<User> findByEmailId(String emailId);

    /**
     * Retrieves a user based on the provided username.
     *
     * @param username The username of the user to retrieve.
     * @return An Optional containing the user with the provided username, if found.
     */
    Optional<User> findByUsername(String username);
}
