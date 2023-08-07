package com.project.moviebooking.repository;

import com.project.moviebooking.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

}
