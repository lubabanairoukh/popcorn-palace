package com.att.tdp.popcorn_palace.repository;

import com.att.tdp.popcorn_palace.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


// Manages booking data in the database, including finding bookings by showtime and seat
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    Optional<Booking> findByShowtime_IdAndSeatNumber(Long showtimeId, int seatNumber);
}
