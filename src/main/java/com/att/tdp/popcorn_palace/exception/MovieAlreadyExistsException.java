package com.att.tdp.popcorn_palace.exception;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String title) {
        super("Movie with title '" + title + "' already exists. To update it, use POST /movies/update/{movieTitle}");
    }
}
