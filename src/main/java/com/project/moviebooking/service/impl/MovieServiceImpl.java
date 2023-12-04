package com.project.moviebooking.service.impl;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.repository.MovieRepository;
import com.project.moviebooking.service.MovieService;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing movies.
 */
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository; // Repository handling Movie entities.

    @Autowired
    private Utils utils; // Utility class for transformation and mapping operations.

    /**
     * Creates a new movie record based on the provided movie request.
     *
     * @param movieRequest The request containing movie details.
     * @return The created movie response.
     */
    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {

        Movie movie = utils.movieRequestToMovieTransformer(movieRequest);

        movie = movieRepository.save(movie);
        log.info("Movie added to the database with {}", movie.getMovieId());

        return utils.movieToMovieResponseTransformer(movie);
    }

    /**
     * Retrieves a list of movies with the provided name using pagination.
     *
     * @param movieName The name of the movie to search for.
     * @param pageable  Pagination information.
     * @return A list of movies with pagination.
     */
    @Override
    @Cacheable(value = "movieCache", key = "#movieName + '_' + #pageable.getPageNumber() + '_' + #pageable.getPageSize()")
    public List<MovieResponse> getAllMovieByMovieName(String movieName, Pageable pageable) {

        return movieRepository.findByMovieName(movieName, pageable)
                .map(utils::movieToMovieResponseTransformer).getContent();
    }

    /**
     * Retrieves a list of movies with the provided name without pagination.
     *
     * @param movieName The name of the movie to search for.
     * @return A list of movies with the given name.
     */
    @Override
    @Cacheable(value = "movieCache", key = "#movieName")
    public List<MovieResponse> getAllMovieByMovieName(String movieName) {

        Optional<List<Movie>> movies = movieRepository.findByMovieName(movieName);

        return (movies.isPresent())
                ? utils.listMovieToListMovieResponseTransformer(movies.get()) : Collections.emptyList();

    }

    /**
     * Retrieves a movie by its unique ID.
     *
     * @param movieId The unique ID of the movie to retrieve.
     * @return The movie details with the given ID.
     */
    @Override
    @Cacheable(value = "movieCache", key = "#movieId")
    public MovieResponse getMovieByMovieId(String movieId) {

        Optional<Movie> movie = movieRepository.findById(movieId);

        return movie.map(value ->
                utils.movieToMovieResponseTransformer(value))
                .orElse(null);

    }
}
