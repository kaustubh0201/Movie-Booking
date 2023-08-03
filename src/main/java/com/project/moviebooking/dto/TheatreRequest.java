package com.project.moviebooking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class TheatreRequest {

    private String theatreName;
    private Map<Integer, Integer> auditoriumNumberToNumberOfSeats;

}
