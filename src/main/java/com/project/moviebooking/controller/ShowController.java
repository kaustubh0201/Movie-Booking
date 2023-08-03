package com.project.moviebooking.controller;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
