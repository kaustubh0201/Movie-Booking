package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreResponse implements Serializable {

    private String theatreId;
    private String theatreName;
    private Map<Integer, Integer> auditoriumNumberToNumberOfSeats;
    private String theatreAddress;
    private String theatreCity;
    private String theatreState;

}


