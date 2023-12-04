package com.project.moviebooking.service.impl;

import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.BookedSeats;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.repository.BookedSeatsRepository;
import com.project.moviebooking.repository.ShowRepository;
import com.project.moviebooking.service.ShowService;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing movie shows.
 */
@Service
@Slf4j
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository; // Repository handling Show entities.

    @Autowired
    private MovieServiceImpl movieService; // Service handling Movie entities.

    @Autowired
    private TheatreServiceImpl theatreService; // Service handling Theatre entities.

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository; // Repository handling BookedSeats entities.

    @Autowired
    private Utils utils; // Utility class for transformation and mapping operations.

    /**
     * Creates a new show based on the provided show request.
     *
     * @param showRequest The request containing show details.
     * @return The created show.
     */
    @Override
    public Show createShow(ShowRequest showRequest) {

        Show show = utils.showRequestToShowTransformer(showRequest);
        show = showRepository.save(show);

        bookedSeatsRepository.save(BookedSeats.builder()
                        .showId(show.getShowId())
                        .allBookedSeats(Collections.emptyList())
                .build());

        log.info("Show added to the database with {}", show.getShowId());
        return show;
    }

    /**
     * Retrieves all shows for a given movie.
     *
     * @param movieName The name of the movie to retrieve shows for.
     * @return A list of shows for the specified movie.
     */
    @Override
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

    /**
     * Retrieves all shows for a given movie in a specific city.
     *
     * @param movieName The name of the movie to retrieve shows for.
     * @param cityName  The name of the city where shows are being searched for.
     * @return A list of shows for the specified movie in the given city.
     */
    @Override
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

    /**
     * Retrieves a show by its unique ID.
     *
     * @param showId The unique ID of the show to retrieve.
     * @return The show details with the given ID.
     */
    @Override
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
