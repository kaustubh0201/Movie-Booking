package com.project.moviebooking.service;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;

import java.util.List;

public interface MovieService {
    void createMovie(MovieRequest movieRequest);
    List<MovieResponse> getAllMovieByMovieName(String movieName);
    MovieResponse getMovieByMovieId(String movieId);
}
