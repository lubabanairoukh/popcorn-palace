package com.att.tdp.popcorn_palace.exception;



// Thrown if a showtime with the given ID doesn't exist
public class ShowtimeNotFoundException extends RuntimeException {
    public ShowtimeNotFoundException(Long id) {
        super("Showtime with ID " + id + " not found.");
    }
}