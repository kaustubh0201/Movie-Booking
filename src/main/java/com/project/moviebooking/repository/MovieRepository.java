package com.project.moviebooking.repository;

import com.project.moviebooking.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<List<Movie>> findByMovieName(String movieName);
    Page<Movie> findByMovieName(String movieName, Pageable pageable);
}
