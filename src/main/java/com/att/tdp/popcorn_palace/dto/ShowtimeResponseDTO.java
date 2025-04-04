package com.att.tdp.popcorn_palace.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

// This provides showtime details including ID, movie ID, price, theater, and start/end times
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeResponseDTO {
    private  Long id;
    private Long movieId;
    private double price;
    private String theater;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

}
