package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.BookingRequestDTO;
import com.att.tdp.popcorn_palace.dto.BookingResponseDTO;
import com.att.tdp.popcorn_palace.entity.Booking;
import com.att.tdp.popcorn_palace.entity.Showtime;
import com.att.tdp.popcorn_palace.exception.SeatAlreadyBookedException;
import com.att.tdp.popcorn_palace.exception.ShowtimeNotFoundException;
import com.att.tdp.popcorn_palace.repository.BookingRepository;
import com.att.tdp.popcorn_palace.repository.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ShowtimeRepository showtimeRepository;

    @InjectMocks
    private BookingService bookingService;

    private BookingRequestDTO validRequest;
    private Showtime validShowtime;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        validRequest = new BookingRequestDTO();
        validRequest.setSeatNumber(5);
        validRequest.setShowtime(1L);
        validRequest.setUserId(UUID.randomUUID());

        validShowtime = new Showtime();
        validShowtime.setId(1L);
        validShowtime.setTheater("Room A");
        validShowtime.setStartTime(OffsetDateTime.now());
        validShowtime.setEndTime(OffsetDateTime.now().plusMinutes(120));
        validShowtime.setPrice(30.0);
    }

    @Test
    void bookTicket_shouldSucceed_whenValidRequestAndSeatAvailable() {
        when(showtimeRepository.findById(1L)).thenReturn(Optional.of(validShowtime));
        when(bookingRepository.findByShowtime_IdAndSeatNumber(1L, 5)).thenReturn(Optional.empty());

        Booking mockSaved = new Booking();
        mockSaved.setBookingId(UUID.randomUUID());
        mockSaved.setSeatNumber(5);
        mockSaved.setShowtime(validShowtime);
        mockSaved.setUserId(validRequest.getUserId());

        when(bookingRepository.save(any(Booking.class))).thenReturn(mockSaved);

        BookingResponseDTO response = bookingService.bookTicket(validRequest);

        assertNotNull(response);
        assertEquals(mockSaved.getBookingId(), response.getBookingId());
    }

    @Test
    void bookTicket_shouldThrowException_ifShowtimeNotFound() {
        when(showtimeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ShowtimeNotFoundException.class, () -> bookingService.bookTicket(validRequest));
    }

    @Test
    void bookTicket_shouldThrowException_ifSeatAlreadyBooked() {
        when(showtimeRepository.findById(1L)).thenReturn(Optional.of(validShowtime));

        Booking existing = new Booking();
        existing.setSeatNumber(5);
        existing.setShowtime(validShowtime);

        when(bookingRepository.findByShowtime_IdAndSeatNumber(1L, 5)).thenReturn(Optional.of(existing));

        assertThrows(SeatAlreadyBookedException.class, () -> bookingService.bookTicket(validRequest));
    }
}

