package com.project.moviebooking.service;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;

import java.util.List;

public interface ShowService {

    void createShow(ShowRequest showRequest);
    List<ShowResponse> getAllShowsByMovie(String movieName);
    List<ShowResponse> getAllShowByMovieAndCity(String movieName, String cityName);
    ShowResponse getShowByShowId(String showId);

}
