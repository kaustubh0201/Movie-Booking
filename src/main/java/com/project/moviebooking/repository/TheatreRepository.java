package com.project.moviebooking.repository;

import com.project.moviebooking.model.Theatre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends MongoRepository<Theatre, String> {
    Optional<List<Theatre>> findByTheatreCityAndTheatreName(String theatreCity, String theatreName);
    Optional<List<Theatre>> findByTheatreCity(String theatreCity);
}
