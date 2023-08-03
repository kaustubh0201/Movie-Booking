package com.project.moviebooking.repository;

import com.project.moviebooking.model.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TheatreRepository extends MongoRepository<Theatre, String> {

}
