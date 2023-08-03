package com.project.moviebooking.service;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.Theatre;
import com.project.moviebooking.repository.TheatreRepository;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private Utils utility;

    public void createTheatre(TheatreRequest theatreRequest) {

        Theatre theatre = Theatre.builder()
                .theatreName(theatreRequest.getTheatreName())
                .auditoriumNumberToNumberOfSeats(theatreRequest.getAuditoriumNumberToNumberOfSeats())
                .theatreAddress(theatreRequest.getTheatreAddress())
                .theatreCity(theatreRequest.getTheatreCity())
                .build();

        theatreRepository.save(theatre);
        log.info("Theatre added to the database with {}", theatre.getTheatreId());

    }

    public List<TheatreResponse> getAllTheatreByTheatreCity(String theatreCity) {

        Optional<List<Theatre>> theatres = theatreRepository.findByTheatreCity(theatreCity);

        return (theatres.isPresent())
                ? utility.listTheatreToListTheatreResponseTransformer(theatres.get()) : Collections.emptyList();
    }

    public List<TheatreResponse> getAllTheatreByTheatreCityAndTheatreName(String theatreCity, String theatreName) {

        Optional<List<Theatre>> theatres = theatreRepository.findByTheatreCityAndTheatreName(theatreCity, theatreName);

        return (theatres.isPresent())
                ? utility.listTheatreToListTheatreResponseTransformer(theatres.get()) : Collections.emptyList();
    }

}
