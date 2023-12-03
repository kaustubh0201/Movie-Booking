package com.project.moviebooking.controller;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.service.impl.ShowServiceImpl;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * This is a controller class for the creation of shows.
 * The API requests for show are declared here.
 * The methods to create a show, get the shows using different parameters are present here.
 */
@RestController
@Slf4j
@RequestMapping("/api/show")
public class ShowController {

    /**
     * This object is for accessing the functions of the show service layer.
     */
    @Autowired
    private ShowServiceImpl showService;

    /**
     * This object is used for access the different utility functions present in the utility class.
     */
    @Autowired
    private Utils utils;

    /**
     * This method is for the creation of show which can only be done by admins.
     * @param showRequest Contains the information of the show that needs to be created.
     * @return A created show body is returned if the theatre is created successfully along with message otherwise
     * in the case of error only error message is returned.
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createShow(@RequestBody ShowRequest showRequest) {

        Map<String, Object> response = new HashMap<>();

        if (!utils.isAdmin()) {
            log.error("Unauthorized user access for creating show with movieId: {} and theatreId: {}",
                    showRequest.getMovieId(), showRequest.getTheatreId());
            response.put("errorMessage", "Shows can only be created by admins.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        try {
            Show show = showService.createShow(showRequest);
            response.put("show", show);
            response.put("message", "Successfully created show!");
            log.info("Show successfully created with showId: {}", show.getShowId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while creating show with movieId: {}, theatreId: {} and error: {}",
                    showRequest.getMovieId(), showRequest.getTheatreId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * This method is used to get the shows by movie.
     * @param movieName This is the name of the movie of which we want all the shows.
     * @return A list with all the show information is returned along with a message otherwise in the case of any error
     * only the error message is returned.
     */
    @GetMapping
    @RequestMapping("/movie")
    public ResponseEntity<Map<String, Object>> getAllShowsByMovie(@RequestParam String movieName) {
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("showList", showService.getAllShowsByMovie(movieName));
            response.put("message", "Successfully fetched all the movies!");
            log.info("Successfully fetched all shows with movieName: {}", movieName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while fetching shows with movieName: {} and error: {}", movieName, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * This method is used to get the show by the movie and respective city.
     * @param movieName This is the name of the movie of which we want all the shows.
     * @param cityName This is the name of the city.
     * @return A list with all the show information is returned along with a message otherwise in the case of any error
     * only the error message is returned.
     */
    @GetMapping
    @RequestMapping("/movie-and-city")
    public ResponseEntity<Map<String, Object>> getAllShowsByMovieAndCity(@RequestParam String movieName,
                                                        @RequestParam String cityName) {
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("showList", showService.getAllShowByMovieAndCity(movieName, cityName));
            response.put("message", "Successfully fetched all the movies!");
            log.info("Successfully fetched all shows with movieName: {} and cityName: {}", movieName, cityName);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while fetching shows with movieName: {}, cityName: {} and error: {}",
                    movieName, cityName, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
