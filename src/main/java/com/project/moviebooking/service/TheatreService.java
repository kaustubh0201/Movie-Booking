package com.project.moviebooking.service;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TheatreService {
    void createTheatre(TheatreRequest theatreRequest);
    Page<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity, Pageable pageable);
    List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity);
    Page<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity,
                                                                   String theatreName, Pageable pageable);
    List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity, String theatreName);
    TheatreResponse getTheatreByTheatreId(String theatreId);
}
