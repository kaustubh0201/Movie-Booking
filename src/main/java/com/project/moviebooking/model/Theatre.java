package com.project.moviebooking.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("theatre")
@Data
@Builder
public class Theatre {

    @Id
    private String theatreId;
    private String theatreName;
    private Map<Integer, Integer> auditoriumNumberToNumberOfSeats;
    private String theatreAddress;
    private
}
