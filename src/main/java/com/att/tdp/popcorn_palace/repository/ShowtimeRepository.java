package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long>{
    List<Showtime> findByTheaterAndStartTimeLessThanAndEndTimeGreaterThan(
            String theater, OffsetDateTime endTime, OffsetDateTime startTime
    );
}
