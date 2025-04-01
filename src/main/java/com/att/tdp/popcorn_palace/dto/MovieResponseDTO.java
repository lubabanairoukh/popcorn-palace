package com.att.tdp.popcorn_palace.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This returns the movie details back to the user
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponseDTO {

    private Long movieId;
    private String title;
    private String genre;
    private int duration;
    private double rating;
    private int releaseYear;



}
