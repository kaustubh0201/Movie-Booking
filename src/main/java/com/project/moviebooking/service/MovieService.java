package com.project.moviebooking.service;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void createMovie(MovieRequest movieRequest) {

        Movie movie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .releaseDate(movieRequest.getReleaseDate())
                .exitDate(movieRequest.getExitDate())
                .movieDuration(movieRequest.getMovieDuration())
                .build();

        movieRepository.save(movie);
        log.info("Movie added to the database with {}", movie.getMovieId());
    }

}
