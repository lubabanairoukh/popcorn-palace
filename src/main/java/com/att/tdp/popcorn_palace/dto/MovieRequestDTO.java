package com.att.tdp.popcorn_palace.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {

    @NotBlank(message = "Title is required and cannot be empty")
    private String title;

    @NotBlank(message = "Genre is required and cannot be empty")
    private String genre;

    @Min(value = 1,message = "Duration must be more than 0 minutes")
    private int duration;

    @Min(value= 0, message = "Rating must be between 0 and 10")
    @Max(value= 10, message = "Rating must be between 0 and 10")
    private double rating;

    private int releaseYear;
}

