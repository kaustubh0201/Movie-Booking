package com.project.moviebooking.service;

import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.repository.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public void createShow(ShowRequest showRequest) {

        Show show = Show.builder()
                .movieName(showRequest.getMovieName())
                .theatreId(showRequest.getTheatreId())
                .movieDuration(showRequest.getMovieDuration())
                .releaseDate(showRequest.getReleaseDate())
                .auditoriumToShowTime(showRequest.getAuditoriumToShowTime())
                .build();

        showRepository.save(show);
        log.info("Show added to the database with {}", show.getShowId());

    }

}
