package com.project.moviebooking.utils;

import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.model.Show;
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

    public ShowResponse showToShowResponseTransformer(Show show,
                                                      MovieResponse movieResponse, TheatreResponse theatreResponse) {

        return ShowResponse.builder()
                .showId(show.getShowId())
                .movieResponse(movieResponse)
                .theatreResponse(theatreResponse)
                .auditorium(show.getAuditorium())
                .showTime(show.getShowTime())
                .build();

    }

    public BookingResponse bookingToBookingResponseTransformer(Booking booking, ShowResponse showResponse) {

        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .showResponse(showResponse)
                .bookedSeats(booking.getBookedSeats())
                .build();
    }

}
