package com.project.moviebooking.service;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    MovieResponse createMovie(MovieRequest movieRequest);
    List<MovieResponse> getAllMovieByMovieName(String movieName);
    List<MovieResponse> getAllMovieByMovieName(String movieName, Pageable pageable);
    MovieResponse getMovieByMovieId(String movieId);
}
