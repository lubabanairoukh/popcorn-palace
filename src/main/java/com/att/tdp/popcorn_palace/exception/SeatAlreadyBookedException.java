package com.att.tdp.popcorn_palace.exception;

// Thrown if the requested seat is already taken for a showtime
public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException(int seatNumber) {
        super("Seat " + seatNumber + " is already booked for this showtime.");
    }
}
