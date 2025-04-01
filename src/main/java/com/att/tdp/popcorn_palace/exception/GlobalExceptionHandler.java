package com.att.tdp.popcorn_palace.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMovieNotFound(MovieNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMovieAlreadyExists(MovieAlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409
    }

    @ExceptionHandler(ShowtimeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleShowtimeNotFound(ShowtimeNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ShowtimeOverlapException.class)
    public ResponseEntity<Map<String, String>> handleShowtimeOverlap(ShowtimeOverlapException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409
    }


    @ExceptionHandler(ShowtimeDurationMismatchException.class)
    public ResponseEntity<Map<String, String>> handleDurationMismatch(ShowtimeDurationMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error); // HTTP 400
    }

}
