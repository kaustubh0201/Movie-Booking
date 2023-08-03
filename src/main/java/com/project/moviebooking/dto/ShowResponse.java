package com.project.moviebooking.dto;

import com.project.moviebooking.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponse {

    private String showId;
    private MovieResponse movieResponse;
    private TheatreResponse theatreResponse;
    private Integer auditorium;
    private Date showTime;

}
