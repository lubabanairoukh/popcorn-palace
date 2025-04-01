package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.ShowtimeRequestDTO;
import com.att.tdp.popcorn_palace.dto.ShowtimeResponseDTO;
import com.att.tdp.popcorn_palace.entity.Movie;
import com.att.tdp.popcorn_palace.entity.Showtime;
import com.att.tdp.popcorn_palace.exception.ShowtimeDurationMismatchException;
import com.att.tdp.popcorn_palace.exception.ShowtimeNotFoundException;
import com.att.tdp.popcorn_palace.exception.ShowtimeOverlapException;
import com.att.tdp.popcorn_palace.repository.ShowtimeRepository;
import com.att.tdp.popcorn_palace.mapper.ShowtimeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShowtimeServiceTest {

    @Mock
    private ShowtimeRepository showtimeRepository;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private ShowtimeService showtimeService;

    private Movie testMovie;
    private ShowtimeRequestDTO validDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testMovie = new Movie();
        testMovie.setMovieId(1L);
        testMovie.setDuration(120);

        validDto = new ShowtimeRequestDTO();
        validDto.setMovieId(1L);
        validDto.setTheater("Theater A");
        validDto.setPrice(25.0);
        validDto.setStartTime(OffsetDateTime.parse("2025-04-01T14:00:00Z"));
        validDto.setEndTime(OffsetDateTime.parse("2025-04-01T16:00:00Z"));
    }

    @Test
    void getShowtimeById_shouldReturnShowtimeResponse() {
        Showtime showtime = ShowtimeMapper.toEntity(validDto, testMovie);
        when(showtimeRepository.findById(1L)).thenReturn(Optional.of(showtime));

        ShowtimeResponseDTO result = showtimeService.getShowtimeById(1L);

        assertNotNull(result);
        assertEquals(validDto.getTheater(), result.getTheater());
    }

    @Test
    void getShowtimeById_shouldThrowException_ifNotFound() {
        when(showtimeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ShowtimeNotFoundException.class, () -> showtimeService.getShowtimeById(99L));
    }

    @Test
    void addShowtime_shouldThrowException_ifDurationMismatch() {
        validDto.setEndTime(validDto.getStartTime().plusMinutes(90)); // invalid

        when(movieService.findMovieByIdOrThrow(1L)).thenReturn(testMovie);

        assertThrows(ShowtimeDurationMismatchException.class, () -> showtimeService.addShowtime(validDto));
    }

    @Test
    void addShowtime_shouldThrowException_ifOverlapping() {
        Showtime existing = ShowtimeMapper.toEntity(validDto, testMovie);
        existing.setId(123L);

        when(movieService.findMovieByIdOrThrow(1L)).thenReturn(testMovie);
        when(showtimeRepository.findByTheaterAndStartTimeLessThanAndEndTimeGreaterThan(
                anyString(), any(), any())).thenReturn(java.util.List.of(existing));

        assertThrows(ShowtimeOverlapException.class, () -> showtimeService.addShowtime(validDto));
    }

    @Test
    void deleteShowtime_shouldThrowException_ifNotFound() {
        when(showtimeRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ShowtimeNotFoundException.class, () -> showtimeService.deleteShowtime(100L));
    }
}