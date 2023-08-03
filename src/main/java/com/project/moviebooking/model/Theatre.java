package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("theatre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {

    @Id
    private String theatreId;
    private String theatreName;
    private Map<Integer, Integer> auditoriumNumberToNumberOfSeats;
    private String theatreAddress;
    private String theatreCity;
    private String theatreState;
}
