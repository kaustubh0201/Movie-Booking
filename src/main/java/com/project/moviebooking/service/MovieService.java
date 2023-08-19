package com.project.moviebooking.service;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.repository.MovieRepository;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Utils utils;

    public void createMovie(MovieRequest movieRequest) {

        Movie movie = Movie.builder()
                .movieName(movieRequest.getMovieName())
                .releaseDate(movieRequest.getReleaseDate())
                .movieDuration(movieRequest.getMovieDuration())
                .build();

        movieRepository.save(movie);
        log.info("Movie added to the database with {}", movie.getMovieId());
    }

    public List<MovieResponse> getAllMovieByMovieName(String movieName) {

        Optional<List<Movie>> movies = movieRepository.findByMovieName(movieName);

        return (movies.isPresent())
                ? utils.listMovieToListMovieResponseTransformer(movies.get()) : Collections.emptyList();

    }

    public MovieResponse getMovieByMovieId(String movieId) {

        Optional<Movie> movie = movieRepository.findById(movieId);

        return movie.map(value -> MovieResponse.builder()
                .movieId(movieId)
                .movieName(movie.get().getMovieName())
                .releaseDate(movie.get().getReleaseDate())
                .movieDuration(movie.get().getMovieDuration())
                .build()).orElse(null);

    }

}
