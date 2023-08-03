package com.project.moviebooking.repository;

import com.project.moviebooking.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<Show, String> {

}
