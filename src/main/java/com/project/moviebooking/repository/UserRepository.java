package com.project.moviebooking.repository;

import com.project.moviebooking.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmailId(String emailId);
    Optional<User> findByUsername(String username);

}
