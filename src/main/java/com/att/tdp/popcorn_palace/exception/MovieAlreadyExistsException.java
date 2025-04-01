package com.att.tdp.popcorn_palace.exception;

// This exception is thrown when you try to add a movie that already exists
public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String title) {
        super("Movie with title '" + title + "' already exists. To update it, use POST /movies/update/{movieTitle}");
    }
}
