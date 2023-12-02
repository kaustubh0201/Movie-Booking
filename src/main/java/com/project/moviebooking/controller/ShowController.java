package com.project.moviebooking.controller;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.service.impl.ShowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowServiceImpl showService;

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody ShowRequest showRequest) {

        Show show = showService.createShow(showRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(show);
    }

    @GetMapping
    @RequestMapping("/movie")
    public ResponseEntity<List<ShowResponse>> getAllShowsByMovie(@RequestParam String movieName) {
        return ResponseEntity.ok(showService.getAllShowsByMovie(movieName));
    }

    @GetMapping
    @RequestMapping("/movie-and-city")
    public ResponseEntity<List<ShowResponse>> getAllShowsByMovieAndCity(@RequestParam String movieName,
                                                        @RequestParam String cityName) {
        return ResponseEntity.ok(showService.getAllShowByMovieAndCity(movieName, cityName));
    }

}
