package com.project.moviebooking.controller;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.service.impl.TheatreServiceImpl;
import com.project.moviebooking.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
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
    public ResponseEntity<TheatreResponse> createTheatre(@RequestBody TheatreRequest theatreRequest) {

        if (!utils.isAdmin()) {
            log.error("Unauthorized user access for creating theatres.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(TheatreResponse.builder().build());
        }

        TheatreResponse theatreResponse = theatreService.createTheatre(theatreRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(theatreResponse);
    }

    @GetMapping
    @RequestMapping("/city")
    public ResponseEntity<List<TheatreResponse>> getTheatreByTheatreCity(@RequestParam String theatreCity,
                                                                         @RequestParam (defaultValue = "0") int page,
                                                                         @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(theatreService.getAllTheatreByTheatreCity(theatreCity, pageable));
    }

    @GetMapping
    @RequestMapping("/city-and-name")
    public ResponseEntity<List<TheatreResponse>> getTheatreByTheatreCityAndTheatreName(@RequestParam String theatreCity,
                                                                       @RequestParam String theatreName,
                                                                       @RequestParam (defaultValue = "0") int page,
                                                                       @RequestParam (defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.of(Optional.of(theatreService
                .getAllTheatreByTheatreCityAndTheatreName(theatreCity, theatreName, pageable)));
    }
}
