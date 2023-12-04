package com.project.moviebooking.service;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface defining operations related to movies.
 * Handles movie creation and retrieval based on different criteria.
 */
public interface MovieService {

    /**
     * Creates a new movie based on the provided movie details.
     *
     * @param movieRequest The request containing details to create a movie.
     * @return The response containing details of the created movie.
     */
    MovieResponse createMovie(MovieRequest movieRequest);

    /**
     * Retrieves a list of movies based on the movie name.
     *
     * @param movieName The name of the movie to search for.
     * @return A list of movie responses matching the provided movie name.
     */
    List<MovieResponse> getAllMovieByMovieName(String movieName);

    /**
     * Retrieves a paginated list of movies based on the movie name.
     *
     * @param movieName The name of the movie to search for.
     * @param pageable  Pagination information (page number, size, etc.).
     * @return A paginated list of movie responses matching the provided movie name.
     */
    List<MovieResponse> getAllMovieByMovieName(String movieName, Pageable pageable);

    /**
     * Retrieves a movie based on the provided movie ID.
     *
     * @param movieId The ID of the movie to retrieve.
     * @return The response containing details of the requested movie.
     */
    MovieResponse getMovieByMovieId(String movieId);
}