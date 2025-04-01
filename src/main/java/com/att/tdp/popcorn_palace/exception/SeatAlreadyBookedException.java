package com.att.tdp.popcorn_palace.exception;

public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException(int seatNumber) {
        super("Seat " + seatNumber + " is already booked for this showtime.");
    }
}
