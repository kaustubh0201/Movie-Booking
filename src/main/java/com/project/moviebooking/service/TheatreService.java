package com.project.moviebooking.service;

import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.model.Theatre;
import com.project.moviebooking.repository.TheatreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public void createTheatre(TheatreRequest theatreRequest) {

        Theatre theatre = Theatre.builder()
                .theatreName(theatreRequest.getTheatreName())
                .auditoriumNumberToNumberOfSeats(theatreRequest.getAuditoriumNumberToNumberOfSeats())
                .build();

        theatreRepository.save(theatre);
        log.info("Theatre added to the database with {}", theatre.getTheatreId());

    }

    public List<Theatre> getAllTheatre

}
