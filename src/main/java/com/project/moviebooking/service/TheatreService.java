package com.project.moviebooking.service;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;

import java.util.List;

public interface TheatreService {
    void createTheatre(TheatreRequest theatreRequest);
    List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity);
    List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity, String theatreName);
    TheatreResponse getTheatreByTheatreId(String theatreId);
}
