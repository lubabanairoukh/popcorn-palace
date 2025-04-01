package com.att.tdp.popcorn_palace.exception;
// Thrown if a movie with the given title doesn't exist
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String title) {
        super("Movie with title '" + title + "' not found.");
    }
}
