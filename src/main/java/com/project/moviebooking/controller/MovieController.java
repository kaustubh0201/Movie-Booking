package com.project.moviebooking.controller;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.service.impl.MovieServiceImpl;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a controller class for the creation of movies.
 * The API requests for movies are declared here.
 * The method to create movies and get movies by different parameters are present here.
 */
@RestController
@Slf4j
@RequestMapping("/api/movie")
public class MovieController {

    /**
     * This object is for accessing the functions of the movie service layer.
     */
    @Autowired
    private MovieServiceImpl movieService;

    /**
     * This object is used for access the different utility functions present in the utility class.
     */
    @Autowired
    private Utils utils;

    /**
     * This method is for the creation of movie which can only be done by admins.
     * @param movieRequest Contains the information of the movie that needs to be created.
     * @return A created movie body is returned if the movie is created successfully along with message otherwise
     * in the case of error only error message is returned.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMovie(@RequestBody MovieRequest movieRequest) {

        Map<String, Object> response = new HashMap<>();

        if (!utils.isAdmin()) {
            log.error("Unauthorized user access for creating movie with movieName: {}", movieRequest.getMovieName());
            response.put("errorMessage", "New movies can only be created by admins.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        try {
            MovieResponse movieResponse = movieService.createMovie(movieRequest);
            response.put("movieResponse", movieResponse);
            response.put("message", "Movie has been successfully created!");
            log.info("Movie created successfully with movieId: {} and movieName: {}",
                    movieResponse.getMovieId(), movieResponse.getMovieName());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while creating movie with movieName: {} with error: {}",
                    movieRequest.getMovieName(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * This method is used to get the movies by the name of the movie.
     * @param movieName This is the name of the movie.
     * @param page This is the page number for the pagination purpose.
     * @param size This is the size of each page the pagination purpose.
     * @return A list with all the movie information is returned along with a message otherwise in the case of any error
     * only the error message is returned. A list is returned because there can be many movies with the exact same name.
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getMovieByMovieName(@RequestParam String movieName,
                                                   @RequestParam (defaultValue = "0") int page,
                                                   @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("movieList", movieService.getAllMovieByMovieName(movieName, pageable));
            response.put("message", "Successfully got all the movies!");
            log.info("Fetched movies with movieName: {}, page: {} and size: {}", movieName, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while fetching movies with movieName: {} with error: {}", movieName, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
