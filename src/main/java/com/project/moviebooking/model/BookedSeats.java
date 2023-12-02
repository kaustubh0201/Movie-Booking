package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("booked_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookedSeats {

    @Id
    private String showId;
    private List<Integer> allBookedSeats;
}
