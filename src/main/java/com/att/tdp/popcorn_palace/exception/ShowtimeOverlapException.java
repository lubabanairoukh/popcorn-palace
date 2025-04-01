package com.att.tdp.popcorn_palace.exception;


// Thrown if a new showtime overlaps with an existing one
public class ShowtimeOverlapException extends RuntimeException {
    public ShowtimeOverlapException(String message) {
        super(message);
    }
}
