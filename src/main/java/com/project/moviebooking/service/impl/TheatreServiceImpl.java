package com.project.moviebooking.service.impl;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.Theatre;
import com.project.moviebooking.repository.TheatreRepository;
import com.project.moviebooking.service.TheatreService;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private Utils utils;

    @Override
    public void createTheatre(TheatreRequest theatreRequest) {

        Theatre theatre = utils.theatreRequestToTheatreTransformer(theatreRequest);

        theatreRepository.save(theatre);
        log.info("Theatre added to the database with {}", theatre.getTheatreId());

    }

    @Override
    @Cacheable(value = "theatreCache", key = "#theatreCity")
    public List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity) {

        Optional<List<Theatre>> theatres = theatreRepository.findByTheatreCity(theatreCity);

        return (theatres.isPresent())
                ? utils.listTheatreToListTheatreResponseTransformer(theatres.get()) : Collections.emptyList();
    }

    @Override
    @Cacheable(value = "theatreCache", key = "#theatreCity + '_' + #theatreName")
    public List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity, String theatreName) {

        Optional<List<Theatre>> theatres = theatreRepository.findByTheatreCityAndTheatreName(theatreCity, theatreName);

        return (theatres.isPresent())
                ? utils.listTheatreToListTheatreResponseTransformer(theatres.get()) : Collections.emptyList();
    }

    @Override
    @Cacheable(value = "theatreCache", key = "#theatreId")
    public TheatreResponse getTheatreByTheatreId(String theatreId) {

        Optional<Theatre> theatre = theatreRepository.findById(theatreId);

        return theatre.map(value -> utils.theatreResponseToTheatreTransformer(value))
                .orElse(null);

    }

}
