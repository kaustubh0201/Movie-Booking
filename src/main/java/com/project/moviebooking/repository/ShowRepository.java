package com.project.moviebooking.repository;

import com.project.moviebooking.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends MongoRepository<Show, String> {

    Optional<List<Show>> findByMovieId(String movieId);
    Optional<List<Show>> findByMovieIdAndTheatreId(String movieId, String theatreId);

}
