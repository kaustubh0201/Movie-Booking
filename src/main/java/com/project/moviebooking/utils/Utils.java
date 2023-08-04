package com.project.moviebooking.utils;

import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.model.Theatre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class Utils {

    private TheatreResponse theatreToTheatreResponseTransformer(Theatre theatre) {

        return TheatreResponse.builder()
                .theatreId(theatre.getTheatreId())
                .theatreName(theatre.getTheatreName())
                .auditoriumNumberToNumberOfSeats(theatre.getAuditoriumNumberToNumberOfSeats())
                .theatreAddress(theatre.getTheatreAddress())
                .theatreCity(theatre.getTheatreCity())
                .build();
    }

    public List<TheatreResponse> listTheatreToListTheatreResponseTransformer(List<Theatre> theatres) {

        return theatres.stream()
                .map(this::theatreToTheatreResponseTransformer)
                .collect(Collectors.toList());

    }

    private MovieResponse movieToMovieResponseTransformer(Movie movie) {

        return MovieResponse.builder()
                .movieId(movie.getMovieId())
                .movieName(movie.getMovieName())
                .releaseDate(movie.getReleaseDate())
                .movieDuration(movie.getMovieDuration())
                .build();

    }

    public List<MovieResponse> listMovieToListMovieResponseTransformer(List<Movie> movies) {

        return movies.stream()
                .map(this::movieToMovieResponseTransformer)
                .collect(Collectors.toList());

    }



}
