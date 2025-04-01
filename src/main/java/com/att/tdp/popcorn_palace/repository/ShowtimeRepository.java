package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;


// Manages showtime data and checks for overlapping times in a theater
public interface ShowtimeRepository extends JpaRepository<Showtime, Long>{
    List<Showtime> findByTheaterAndStartTimeLessThanAndEndTimeGreaterThan(
            String theater, OffsetDateTime endTime, OffsetDateTime startTime
    );
}
