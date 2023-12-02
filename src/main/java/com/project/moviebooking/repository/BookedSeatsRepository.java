package com.project.moviebooking.repository;

import com.project.moviebooking.model.BookedSeats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookedSeatsRepository extends MongoRepository<BookedSeats, String> {

}
