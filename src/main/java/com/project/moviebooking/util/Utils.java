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

/**
 * Utility class that provides transformation methods for DTOs and entities,
 * as well as some helper functions related to user authentication and roles.
 */
@Component
public class Utils {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Transforms a Theatre entity into a TheatreResponse DTO.
     *
     * @param theatre The Theatre entity to transform.
     * @return A TheatreResponse DTO.
     */
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

    /**
     * Transforms a list of Theatre entities into a list of TheatreResponse DTOs.
     *
     * @param theatres List of Theatre entities to transform.
     * @return A list of TheatreResponse DTOs.
     */
    public List<TheatreResponse> listTheatreToListTheatreResponseTransformer(List<Theatre> theatres) {

        return theatres.stream()
                .map(this::theatreToTheatreResponseTransformer)
                .collect(Collectors.toList());

    }

    /**
     * Transforms a Movie entity into a MovieResponse DTO.
     *
     * @param movie The Movie entity to transform.
     * @return A MovieResponse DTO.
     */
    public MovieResponse movieToMovieResponseTransformer(Movie movie) {

        return MovieResponse.builder()
                .movieId(movie.getMovieId())
                .movieName(movie.getMovieName())
                .releaseDate(movie.getReleaseDate())
                .movieDuration(movie.getMovieDuration())
                .build();

    }

    /**
     * Transforms a list of Movie entities into a list of MovieResponse DTOs.
     *
     * @param movies List of Movie entities to transform.
     * @return A list of MovieResponse DTOs.
     */
    public List<MovieResponse> listMovieToListMovieResponseTransformer(List<Movie> movies) {

        return movies.stream()
                .map(this::movieToMovieResponseTransformer)
                .collect(Collectors.toList());

    }

    /**
     * Transforms a Show entity into a ShowResponse DTO.
     *
     * @param show           The Show entity to transform.
     * @param movieResponse  The associated MovieResponse DTO.
     * @param theatreResponse The associated TheatreResponse DTO.
     * @return A ShowResponse DTO.
     */
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

    /**
     * Transforms a Booking entity into a BookingResponse DTO.
     *
     * @param booking     The Booking entity to transform.
     * @param showResponse The associated ShowResponse DTO.
     * @return A BookingResponse DTO.
     */
    public BookingResponse bookingToBookingResponseTransformer(Booking booking, ShowResponse showResponse) {

        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .showResponse(showResponse)
                .bookedSeats(booking.getBookedSeats())
                .build();
    }

    /**
     * Transforms a UserRequest DTO into a User entity.
     *
     * @param userRequest The UserRequest DTO to transform.
     * @return A User entity.
     */
    public User userRequestToUserTransformer(UserRequest userRequest) {

        return User.builder()
                .name(userRequest.getName())
                .username(userRequest.getUsername())
                .emailId(userRequest.getEmailId())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(List.of("ROLE_USER"))
                .build();
    }

    /**
     * Transforms a UserRequest DTO into a User entity and sets OTP and verification status.
     *
     * @param userRequest The UserRequest DTO to transform.
     * @param otp         The OTP for user verification.
     * @return A User entity with OTP and verification status.
     */
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

    /**
     * Transforms a TheatreRequest DTO into a Theatre entity.
     *
     * @param theatreRequest The TheatreRequest DTO to transform.
     * @return A Theatre entity.
     */
    public Theatre theatreRequestToTheatreTransformer(TheatreRequest theatreRequest) {

        return Theatre.builder()
                .theatreName(theatreRequest.getTheatreName())
                .auditoriumNumberToNumberOfSeats(theatreRequest.getAuditoriumNumberToNumberOfSeats())
                .theatreAddress(theatreRequest.getTheatreAddress())
                .theatreCity(theatreRequest.getTheatreCity())
                .theatreState(theatreRequest.getTheatreState())
                .build();

    }

    /**
     * Transforms a TheatreResponse DTO into a Theatre entity.
     *
     * @param theatre The TheatreResponse DTO to transform.
     * @return A Theatre entity.
     */
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

    /**
     * Transforms a ShowRequest DTO into a Show entity.
     *
     * @param showRequest The ShowRequest DTO to transform.
     * @return A Show entity.
     */
    public Show showRequestToShowTransformer(ShowRequest showRequest) {

        return Show.builder()
                .movieId(showRequest.getMovieId())
                .theatreId(showRequest.getTheatreId())
                .auditorium(showRequest.getAuditorium())
                .showTime(showRequest.getShowTime())
                .build();

    }

    /**
     * Transforms a MovieRequest DTO into a Movie entity.
     *
     * @param movieRequest The MovieRequest DTO to transform.
     * @return A Movie entity.
     */
    public Movie movieRequestToMovieTransformer(MovieRequest movieRequest) {

        return Movie.builder()
                .movieName(movieRequest.getMovieName())
                .releaseDate(movieRequest.getReleaseDate())
                .movieDuration(movieRequest.getMovieDuration())
                .build();

    }

    /**
     * Transforms a BookingRequest DTO into a Booking entity.
     *
     * @param bookingRequest The BookingRequest DTO to transform.
     * @return A Booking entity.
     */
    public Booking bookingRequestToBooking(BookingRequest bookingRequest) {

        String username = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();

        return Booking.builder()
                .showId(bookingRequest.getShowId())
                .bookedSeats(bookingRequest.getBookedSeats())
                .username(username)
                .build();

    }

    /**
     * Checks if the authenticated user has admin role.
     *
     * @return True if the user has admin role, false otherwise.
     */
    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getAuthorities() != null) {

            return authentication.getAuthorities().stream()
                    .anyMatch(authority -> new SimpleGrantedAuthority("ROLE_ADMIN").equals(authority));
        }

        return false;
    }

    /**
     * Retrieves the username of the authenticated user.
     *
     * @return The username of the authenticated user.
     */
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() != null) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return Constants.EMPTY_STRING;
    }

    /**
     * Encodes the provided password using the password encoder.
     *
     * @param password The password to encode.
     * @return The encoded password.
     */
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
