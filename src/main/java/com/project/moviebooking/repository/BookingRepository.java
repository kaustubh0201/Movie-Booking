package com.project.moviebooking.repository;

import com.project.moviebooking.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, String> {

    Optional<List<Booking>> findByUsername(String username);
    Page<Booking> findByUsername(String username, Pageable pageable);
}
