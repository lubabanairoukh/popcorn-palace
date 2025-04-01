package com.att.tdp.popcorn_palace.entity;

import com.att.tdp.popcorn_palace.entity.Showtime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


// Represents a booking record in the database with seat, user, and showtime info
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue
    private UUID bookingId;

    private int seatNumber;

    private UUID userId;


    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;
}
