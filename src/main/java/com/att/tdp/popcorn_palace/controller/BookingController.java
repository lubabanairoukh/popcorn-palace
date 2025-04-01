package com.att.tdp.popcorn_palace.controller;

import com.att.tdp.popcorn_palace.dto.BookingRequestDTO;
import com.att.tdp.popcorn_palace.dto.BookingResponseDTO;
import com.att.tdp.popcorn_palace.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This controller handles booking requests from users

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookTicket(@RequestBody @Valid BookingRequestDTO dto) {
        BookingResponseDTO response = bookingService.bookTicket(dto);
        return ResponseEntity.ok(response);
    }
}
