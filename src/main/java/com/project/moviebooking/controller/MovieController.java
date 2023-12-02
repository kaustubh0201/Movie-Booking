package com.project.moviebooking.controller;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.service.impl.MovieServiceImpl;
import com.project.moviebooking.util.Utils;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private Utils utils;

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest) {

        if (!utils.isAdmin()) {
            log.error("Unauthorized user access for creating movies.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(MovieResponse.builder().build());
        }

        MovieResponse movieResponse = movieService.createMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovieByMovieName(@RequestParam String movieName,
                                                   @RequestParam (defaultValue = "0") int page,
                                                   @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.getAllMovieByMovieName(movieName, pageable));
    }
}
