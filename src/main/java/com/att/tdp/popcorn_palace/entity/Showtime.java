package com.att.tdp.popcorn_palace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "showtimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Double price;
    private String theater;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;


    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();


    public Showtime(Movie movie,  Double price, String theater, OffsetDateTime startTime, OffsetDateTime endTime) {
        this.movie = movie;
        this.price = price;
        this.theater = theater;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
