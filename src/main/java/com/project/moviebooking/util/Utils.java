package com.project.moviebooking.util;

import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.dto.BookingRequest;
import com.project.moviebooking.dto.BookingResponse;
import com.project.moviebooking.dto.MovieRequest;
import com.project.moviebooking.dto.MovieResponse;
import com.project.moviebooking.dto.ShowRequest;
import com.project.moviebooking.dto.ShowResponse;
import com.project.moviebooking.dto.TheatreRequest;
import com.project.moviebooking.dto.TheatreResponse;
import com.project.moviebooking.dto.UserRequest;
import com.project.moviebooking.model.Booking;
import com.project.moviebooking.model.Movie;
import com.project.moviebooking.model.Show;
import com.project.moviebooking.model.Theatre;
import com.project.moviebooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class Utils {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TheatreResponse theatreToTheatreResponseTransformer(Theatre theatre) {

        return TheatreResponse.builder()
                .theatreId(theatre.getTheatreId())
                .theatreName(theatre.getTheatreName())
                .auditoriumNumberToNumberOfSeats(theatre.getAuditoriumNumberToNumberOfSeats())
                .theatreAddress(theatre.getTheatreAddress())
                .theatreCity(theatre.getTheatreCity())
                .theatreState(theatre.getTheatreState())
                .build();
    }

    public List<TheatreResponse> listTheatreToListTheatreResponseTransformer(List<Theatre> theatres) {

        return theatres.stream()
                .map(this::theatreToTheatreResponseTransformer)
                .collect(Collectors.toList());

    }

    public MovieResponse movieToMovieResponseTransformer(Movie movie) {

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

    public User userRequestToUserTransformer(UserRequest userRequest) {

        return User.builder()
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .emailId(userRequest.getEmailId())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(List.of("ROLE_USER"))
                .build();
    }

    public User userRequestToUserTransformer(UserRequest userRequest, String otp) {

        return User.builder()
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .emailId(userRequest.getEmailId())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(List.of("ROLE_USER"))
                .otp(otp)
                .isVerified(false)
                .build();
    }

    public Theatre theatreRequestToTheatreTransformer(TheatreRequest theatreRequest) {

        return Theatre.builder()
                .theatreName(theatreRequest.getTheatreName())
                .auditoriumNumberToNumberOfSeats(theatreRequest.getAuditoriumNumberToNumberOfSeats())
                .theatreAddress(theatreRequest.getTheatreAddress())
                .theatreCity(theatreRequest.getTheatreCity())
                .theatreState(theatreRequest.getTheatreState())
                .build();

    }

    public TheatreResponse theatreResponseToTheatreTransformer(Theatre theatre) {

        return TheatreResponse.builder()
                .theatreId(theatre.getTheatreId())
                .theatreName(theatre.getTheatreName())
                .auditoriumNumberToNumberOfSeats(theatre.getAuditoriumNumberToNumberOfSeats())
                .theatreAddress(theatre.getTheatreAddress())
                .theatreCity(theatre.getTheatreCity())
                .theatreState(theatre.getTheatreState())
                .build();

    }

    public Show showRequestToShowTransformer(ShowRequest showRequest) {

        return Show.builder()
                .movieId(showRequest.getMovieId())
                .theatreId(showRequest.getTheatreId())
                .auditorium(showRequest.getAuditorium())
                .showTime(showRequest.getShowTime())
                .build();

    }

    public Movie movieRequestToMovieTransformer(MovieRequest movieRequest) {

        return Movie.builder()
                .movieName(movieRequest.getMovieName())
                .releaseDate(movieRequest.getReleaseDate())
                .movieDuration(movieRequest.getMovieDuration())
                .build();

    }

    public Booking bookingRequestToBooking(BookingRequest bookingRequest) {

        String username = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();

        return Booking.builder()
                .showId(bookingRequest.getShowId())
                .bookedSeats(bookingRequest.getBookedSeats())
                .username(username)
                .build();

    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getAuthorities() != null) {

            return authentication.getAuthorities().stream()
                    .anyMatch(authority -> new SimpleGrantedAuthority("ROLE_ADMIN").equals(authority));
        }

        return false;
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return Constants.EMPTY_STRING;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
