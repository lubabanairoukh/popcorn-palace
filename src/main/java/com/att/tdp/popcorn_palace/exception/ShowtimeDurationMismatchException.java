package com.att.tdp.popcorn_palace.exception;


// Thrown if the show's timing doesn't match the movie's duration requirements
public class ShowtimeDurationMismatchException extends RuntimeException {
    public ShowtimeDurationMismatchException(String message) {
        super(message);
    }
}
