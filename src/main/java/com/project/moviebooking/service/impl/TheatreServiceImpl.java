package com.project.moviebooking.service.impl;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.Theatre;
import com.project.moviebooking.repository.TheatreRepository;
import com.project.moviebooking.service.TheatreService;
import com.project.moviebooking.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing theatre entities.
 */
@Service
@Slf4j
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository; // Repository handling Theatre entities.

    @Autowired
    private Utils utils; // Utility class for transformation and mapping operations.

    /**
     * Creates a new theatre based on the provided theatre request.
     *
     * @param theatreRequest The request containing theatre details.
     * @return The created theatre.
     */
    @Override
    public TheatreResponse createTheatre(TheatreRequest theatreRequest) {

        Theatre theatre = utils.theatreRequestToTheatreTransformer(theatreRequest);

        theatre = theatreRepository.save(theatre);
        log.info("Theatre added to the database with {}", theatre.getTheatreId());

        return utils.theatreToTheatreResponseTransformer(theatre);
    }

    /**
     * Retrieves all theatres in a specific city with pagination support.
     *
     * @param theatreCity The city name to filter theatres.
     * @param pageable    Pagination information.
     * @return A paginated list of theatres in the given city.
     */
    @Override
    @Cacheable(value = "theatreCache",
            key = "#theatreCity + '_' + #pageable.getPageNumber() + '_' + #pageable.getPageSize()")
    public List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity, Pageable pageable) {

        return theatreRepository.findByTheatreCity(theatreCity, pageable)
                .map(utils::theatreToTheatreResponseTransformer).getContent();
    }

    /**
     * Retrieves all theatres in a specific city.
     *
     * @param theatreCity The city name to filter theatres.
     * @return A list of theatres in the given city.
     */
    @Override
    @Cacheable(value = "theatreCache", key = "#theatreCity")
    public List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity) {

        Optional<List<Theatre>> theatres = theatreRepository.findByTheatreCity(theatreCity);

        return (theatres.isPresent())
                ? utils.listTheatreToListTheatreResponseTransformer(theatres.get()) : Collections.emptyList();
    }

    /**
     * Retrieves theatres by city and name with pagination support.
     *
     * @param theatreCity  The city name to filter theatres.
     * @param theatreName  The name of the theatre to search for.
     * @param pageable     Pagination information.
     * @return A paginated list of theatres by city and name.
     */

    @Override
    @Cacheable(value = "theatreCache",
            key = "#theatreCity + '_' + #theatreName + '_' + #pageable.getPageNumber() + '_' + #pageable.getPageSize()")
    public List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity, String theatreName,
                                                                          Pageable pageable) {
        return theatreRepository.findByTheatreCityAndTheatreName(theatreCity, theatreName, pageable)
                .map(utils::theatreToTheatreResponseTransformer).getContent();
    }

    /**
     * Retrieves theatres by city and name.
     *
     * @param theatreCity The city name to filter theatres.
     * @param theatreName The name of the theatre to search for.
     * @return A list of theatres by city and name.
     */
    @Override
    @Cacheable(value = "theatreCache", key = "#theatreCity + '_' + #theatreName")
    public List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity, String theatreName) {

        Optional<List<Theatre>> theatres = theatreRepository.findByTheatreCityAndTheatreName(theatreCity, theatreName);

        return (theatres.isPresent())
                ? utils.listTheatreToListTheatreResponseTransformer(theatres.get()) : Collections.emptyList();
    }

    /**
     * Retrieves a theatre by its unique ID.
     *
     * @param theatreId The unique ID of the theatre to retrieve.
     * @return The theatre details with the given ID.
     */
    @Override
    @Cacheable(value = "theatreCache", key = "#theatreId")
    public TheatreResponse getTheatreByTheatreId(String theatreId) {

        Optional<Theatre> theatre = theatreRepository.findById(theatreId);

        return theatre.map(value -> utils.theatreResponseToTheatreTransformer(value))
                .orElse(null);
    }
}
