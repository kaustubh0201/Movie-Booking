package com.project.moviebooking.service;

import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.repository.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TheatreService theatreService;

    public void createShow(ShowRequest showRequest) {

        Show show = Show.builder()
                .movieId(showRequest.getMovieId())
                .theatreId(showRequest.getTheatreId())
                .auditorium(showRequest.getAuditorium())
                .showTime(showRequest.getShowTime())
                .build();

        showRepository.save(show);
        log.info("Show added to the database with {}", show.getShowId());

    }

    public List<ShowResponse> getAllShowsByMovie(String movieName) {

        List<MovieResponse> movies = movieService.getAllMovieByMovieName(movieName);

        return movies.stream()
                .flatMap(movieResponse -> showRepository.findByMovieId(movieResponse.getMovieId())
                        .orElse(Collections.emptyList()).stream()
                        .map(show -> {
                            TheatreResponse theatre = theatreService.getTheatreByTheatreId(show.getTheatreId());
                            return ShowResponse.builder()
                                    .showId(show.getShowId())
                                    .movieResponse(movieResponse)
                                    .theatreResponse(theatre)
                                    .auditorium(show.getAuditorium())
                                    .showTime(show.getShowTime())
                                    .build();
                        })).toList();

    }
}
