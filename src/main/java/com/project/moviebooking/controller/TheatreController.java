package com.project.moviebooking.controller;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.service.impl.TheatreServiceImpl;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/theatre")
public class TheatreController {

    @Autowired
    private TheatreServiceImpl theatreService;

    @Autowired
    private Utils utils;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createTheatre(@RequestBody TheatreRequest theatreRequest) {
        Map<String, Object> response = new HashMap<>();

        if (!utils.isAdmin()) {
            log.error("Unauthorized user access for creating theatre with theatreName: {}",
                    theatreRequest.getTheatreName());
            response.put("errorMessage", "Theatre can only be created by admins.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        try {
            TheatreResponse theatreResponse = theatreService.createTheatre(theatreRequest);
            response.put("theatre", theatreResponse);
            response.put("message", "Theatre has been created successfully!");
            log.info("Theatre has been created successfully with theatreId: {}", theatreResponse.getTheatreId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while creating theatre with theatreName: {}, theatreAddress: {}, theatreCity: {} and error: {}",
                    theatreRequest.getTheatreName(), theatreRequest.getTheatreAddress(), theatreRequest.getTheatreCity(),
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    @RequestMapping("/city")
    public ResponseEntity<Map<String, Object>> getTheatreByTheatreCity(@RequestParam String theatreCity,
                                                                         @RequestParam (defaultValue = "0") int page,
                                                                         @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("theatreList", theatreService.getAllTheatreByTheatreCity(theatreCity, pageable));
            response.put("message", "Successfully fetched theatres by theatre city.");
            log.info("Successfully fetched theatres with theatreCity: {}, page: {} and size: {}",
                    theatreCity, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while fetching theatre by theatreCity: {}, page: {}, size: {} and error: {}",
                    theatreCity, page, size, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    @RequestMapping("/city-and-name")
    public ResponseEntity<Map<String, Object>> getTheatreByTheatreCityAndTheatreName(@RequestParam String theatreCity,
                                                                       @RequestParam String theatreName,
                                                                       @RequestParam (defaultValue = "0") int page,
                                                                       @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("theatreList", theatreService
                    .getAllTheatreByTheatreCityAndTheatreName(theatreCity, theatreName, pageable));
            response.put("message", "Successfully fetched theatres!");
            log.info("Successfully fetched theatre with theatreCity: {}, theatreName: {}, page: {} and size: {}",
                    theatreCity, theatreName, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            log.error("Error while fetching theatre with theatreCity: {}, theatreName: {}, page: {}, size: {} and error: {}",
                    theatreCity, theatreName, page, size, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
