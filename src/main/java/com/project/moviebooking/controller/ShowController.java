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

@RestController
@Slf4j
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowServiceImpl showService;

    @Autowired
    private Utils utils;

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
