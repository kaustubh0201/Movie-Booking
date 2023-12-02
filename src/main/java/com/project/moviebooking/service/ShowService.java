package com.project.moviebooking.service;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.model.Show;

import java.util.List;

public interface ShowService {

    Show createShow(ShowRequest showRequest);
    List<ShowResponse> getAllShowsByMovie(String movieName);
    List<ShowResponse> getAllShowByMovieAndCity(String movieName, String cityName);
    ShowResponse getShowByShowId(String showId);

}
