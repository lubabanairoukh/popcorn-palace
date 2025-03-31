package com.att.tdp.popcorn_palace.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String title) {
        super("Movie with title '" + title + "' not found.");
    }
}
