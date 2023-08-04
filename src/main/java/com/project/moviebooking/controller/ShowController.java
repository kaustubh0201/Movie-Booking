package com.project.moviebooking.controller;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createShow(@RequestBody ShowRequest showRequest) {

        showService.createShow(showRequest);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/movie")
    public List<ShowResponse> getAllShowsByMovie(@RequestParam String movieName) {

        return showService.getAllShowsByMovie(movieName);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/movie-and-city")
    public List<ShowResponse> getAllShowsByMovieAndCity(@RequestParam String movieName,
                                                        @RequestParam String cityName) {

        return showService.getAllShowByMovieAndCity(movieName, cityName);

    }

}
