package com.att.tdp.popcorn_palace.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;


@Getter
@Setter
@NoArgsConstructor
public class ShowtimeRequestDTO  {

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    private double price;

    @NotNull(message = "Theater name is required.")
    private String theater;

    @NotNull(message = "Start time is required.")
    private OffsetDateTime startTime;

    @NotNull(message = "End time is required.")
    private OffsetDateTime endTime;
}
