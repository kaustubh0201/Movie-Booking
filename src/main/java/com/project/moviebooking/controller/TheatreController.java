package com.project.moviebooking.controller;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.service.impl.TheatreServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/theatre")
@RequiredArgsConstructor
public class TheatreController {

    @Autowired
    private TheatreServiceImpl theatreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTheatre(@RequestBody TheatreRequest theatreRequest) {
        theatreService.createTheatre(theatreRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/city")
    public List<TheatreResponse> getTheatreByTheatreCity(@RequestParam String theatreCity) {
        return theatreService.getAllTheatreByTheatreCity(theatreCity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/city-and-name")
    public List<TheatreResponse> getTheatreByTheatreCityAndTheatreName(@RequestParam String theatreCity,
                                                                       @RequestParam String theatreName) {
        return theatreService.getAllTheatreByTheatreCityAndTheatreName(theatreCity, theatreName);
    }
}
