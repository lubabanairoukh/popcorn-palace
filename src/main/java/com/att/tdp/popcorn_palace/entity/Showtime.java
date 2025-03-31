package com.att.tdp.popcorn_palace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Showtime")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Long movieId;
    private Double price;
    private String theater;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;



    public Showtime(Long movie,  Double price, String theater, OffsetDateTime startTime, OffsetDateTime endTime) {
        this.movieId = movie;
        this.price = price;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
