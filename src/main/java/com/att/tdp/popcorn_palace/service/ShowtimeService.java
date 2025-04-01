package com.att.tdp.popcorn_palace.service;

import com.att.tdp.popcorn_palace.dto.ShowtimeRequestDTO;
import com.att.tdp.popcorn_palace.dto.ShowtimeResponseDTO;
import com.att.tdp.popcorn_palace.entity.Movie;
import com.att.tdp.popcorn_palace.entity.Showtime;
import com.att.tdp.popcorn_palace.exception.ShowtimeDurationMismatchException;
import com.att.tdp.popcorn_palace.exception.ShowtimeNotFoundException;
import com.att.tdp.popcorn_palace.exception.ShowtimeOverlapException;
import com.att.tdp.popcorn_palace.mapper.ShowtimeMapper;
import com.att.tdp.popcorn_palace.repository.MovieRepository;
import com.att.tdp.popcorn_palace.repository.ShowtimeRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final MovieService movieService;

    public ShowtimeService(ShowtimeRepository showtimeRepository, MovieService movieService) {
        this.showtimeRepository = showtimeRepository;
        this.movieService = movieService;
    }

    public ShowtimeResponseDTO getShowtimeById(Long id) {
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new ShowtimeNotFoundException(id));

        return ShowtimeMapper.toDTO(showtime);
    }


    public ShowtimeResponseDTO addShowtime(ShowtimeRequestDTO dto) {
        validateShowtimeDurationMatchesMovie(dto);

        checkForOverlap(dto.getTheater(), dto.getStartTime(), dto.getEndTime(), null);

        Showtime showtime = ShowtimeMapper.toEntity(dto);
        Showtime saved = showtimeRepository.save(showtime);

        return ShowtimeMapper.toDTO(saved);
    }

    public void updateShowtime(Long id, ShowtimeRequestDTO dto) {
        Showtime existingShowtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new ShowtimeNotFoundException(id));
        validateShowtimeDurationMatchesMovie(dto);



        checkForOverlap(dto.getTheater(), dto.getStartTime(), dto.getEndTime(), id);

        ShowtimeMapper.updateEntity(existingShowtime, dto);

        showtimeRepository.save(existingShowtime);
    }

    public void deleteShowtime(Long id) {
        if (!showtimeRepository.existsById(id)) {
            throw new ShowtimeNotFoundException(id);
        }

        showtimeRepository.deleteById(id);
    }


    private void checkForOverlap(String theater, OffsetDateTime startTime, OffsetDateTime endTime, Long excludeId) {
        List<Showtime> overlapping = showtimeRepository
                .findByTheaterAndStartTimeLessThanAndEndTimeGreaterThan(theater, endTime, startTime);

        boolean conflictExists = overlapping.stream()
                .anyMatch(showtime -> !showtime.getId().equals(excludeId));

        if (conflictExists) {
            throw new ShowtimeOverlapException("Overlapping showtime exists in the same theater.");
        }
    }

    private void validateShowtimeDurationMatchesMovie(ShowtimeRequestDTO dto) {
        Movie movie = movieService.findMovieByIdOrThrow(dto.getMovieId());

        long expectedMinutes = movie.getDuration();
        long actualMinutes = Duration.between(dto.getStartTime(), dto.getEndTime()).toMinutes();

        if (actualMinutes != expectedMinutes) {
            throw new ShowtimeDurationMismatchException("Showtime duration does not match the movie duration (" + expectedMinutes + " minutes).");
        }
    }


}
