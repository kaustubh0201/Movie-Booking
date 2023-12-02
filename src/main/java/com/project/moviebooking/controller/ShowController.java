package com.project.moviebooking.controller;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
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

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowServiceImpl showService;

    @Autowired
    private Utils utils;

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody ShowRequest showRequest) {

        if (!utils.isAdmin()) {
            log.error("Unauthorized user access for creating shows.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Show.builder().build());
        }

        try {
            Show show = showService.createShow(showRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(show);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Show.builder().build());
        }
    }

    @GetMapping
    @RequestMapping("/movie")
    public ResponseEntity<List<ShowResponse>> getAllShowsByMovie(@RequestParam String movieName) {
        try {
            return ResponseEntity.ok(showService.getAllShowsByMovie(movieName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping
    @RequestMapping("/movie-and-city")
    public ResponseEntity<List<ShowResponse>> getAllShowsByMovieAndCity(@RequestParam String movieName,
                                                        @RequestParam String cityName) {
        try {
            return ResponseEntity.ok(showService.getAllShowByMovieAndCity(movieName, cityName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
