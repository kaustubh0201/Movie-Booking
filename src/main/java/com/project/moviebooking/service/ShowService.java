package com.project.moviebooking.service;

import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.repository.ShowRepository;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    @Autowired
    private Utils utils;

    public void createShow(ShowRequest showRequest) {

        Show show = utils.showRequestToShowTransformer(showRequest);

        showRepository.save(show);
        log.info("Show added to the database with {}", show.getShowId());

    }

    @Cacheable(value = "showCache", key = "#movieName")
    public List<ShowResponse> getAllShowsByMovie(String movieName) {

        List<MovieResponse> movies = movieService.getAllMovieByMovieName(movieName);

        return movies.stream()
                .flatMap(movieResponse -> showRepository.findByMovieId(movieResponse.getMovieId())
                        .orElse(Collections.emptyList()).stream()
                        .map(show -> {
                            TheatreResponse theatre = theatreService.getTheatreByTheatreId(show.getTheatreId());
                            return utils.showToShowResponseTransformer(show, movieResponse, theatre);
                        })).toList();

    }

    @Cacheable(value = "showCache", key = "#movieName + '_' + #cityName")
    public List<ShowResponse> getAllShowByMovieAndCity(String movieName, String cityName) {

        List<TheatreResponse> theatres = theatreService.getAllTheatreByTheatreCity(cityName);
        List<MovieResponse> movies = movieService.getAllMovieByMovieName(movieName);

        return theatres.stream()
                .flatMap(theatreResponse ->
                        movies.stream()
                                .flatMap(movieResponse ->
                                        showRepository.findByMovieIdAndTheatreId(movieResponse.getMovieId(),
                                                        theatreResponse.getTheatreId())
                                                .orElse(Collections.emptyList())
                                                .stream()
                                                .map(show -> utils.showToShowResponseTransformer(show, movieResponse,
                                                        theatreResponse))
                                )
                ).distinct().collect(Collectors.toList());
    }

    @Cacheable(value = "showCache", key = "#showId")
    public ShowResponse getShowByShowId(String showId) {

        Optional<Show> show = showRepository.findByShowId(showId);

        if (show.isEmpty()) {
            log.info("No Show found with the given showId: {}", showId);
            return null;
        }

        MovieResponse movieResponse = movieService
                .getMovieByMovieId(show.get().getMovieId());

        if (movieResponse == null) {
            log.info("No Movie found with the given movieId: {}", show.get().getMovieId());
        }

        TheatreResponse theatreResponse = theatreService
                .getTheatreByTheatreId(show.get().getTheatreId());

        if (theatreResponse == null) {
            log.info("No theatre found with the given theatreId: {}", show.get().getTheatreId());
        }

        return utils.showToShowResponseTransformer(show.get(), movieResponse, theatreResponse);
    }
}
