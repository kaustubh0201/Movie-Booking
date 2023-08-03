package com.project.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequest {

    private String movieName;
    private String theatreId;
    private Integer movieDuration;
    private Map<Integer, List<String>> auditoriumToShowTime;

}
