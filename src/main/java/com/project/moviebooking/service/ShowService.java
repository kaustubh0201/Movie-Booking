package com.project.moviebooking.service;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.model.Show;

import java.util.List;

/**
 * Service interface defining operations related to movie shows.
 * Handles creation and retrieval of shows based on different criteria.
 */
public interface ShowService {

    /**
     * Creates a new show based on the provided show details.
     *
     * @param showRequest The request containing details to create a show.
     * @return The created show.
     */
    Show createShow(ShowRequest showRequest);

    /**
     * Retrieves a list of shows for a given movie.
     *
     * @param movieName The name of the movie to retrieve shows for.
     * @return A list of show responses for the provided movie name.
     */
    List<ShowResponse> getAllShowsByMovie(String movieName);

    /**
     * Retrieves a list of shows for a given movie and city.
     *
     * @param movieName The name of the movie to retrieve shows for.
     * @param cityName  The name of the city where shows are being searched.
     * @return A list of show responses for the provided movie name and city name.
     */
    List<ShowResponse> getAllShowByMovieAndCity(String movieName, String cityName);

    /**
     * Retrieves a specific show based on the show ID.
     *
     * @param showId The ID of the show to retrieve.
     * @return The show response corresponding to the provided show ID.
     */
    ShowResponse getShowByShowId(String showId);
}