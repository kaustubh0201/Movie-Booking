package com.project.moviebooking.service.impl;

import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.repository.MovieRepository;
import com.project.moviebooking.service.MovieService;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Utils utils;

    @Override
    public void createMovie(MovieRequest movieRequest) {

        Movie movie = utils.movieRequestToMovieTransformer(movieRequest);

        movieRepository.save(movie);
        log.info("Movie added to the database with {}", movie.getMovieId());
    }

    @Override
    @Cacheable(value = "movieCache", key = "#movieName")
    public List<MovieResponse> getAllMovieByMovieName(String movieName) {

        Optional<List<Movie>> movies = movieRepository.findByMovieName(movieName);

        return (movies.isPresent())
                ? utils.listMovieToListMovieResponseTransformer(movies.get()) : Collections.emptyList();

    }

    @Override
    @Cacheable(value = "movieCache", key = "#movieId")
    public MovieResponse getMovieByMovieId(String movieId) {

        Optional<Movie> movie = movieRepository.findById(movieId);

        return movie.map(value ->
                utils.movieToMovieResponse(value))
                .orElse(null);

    }

}
