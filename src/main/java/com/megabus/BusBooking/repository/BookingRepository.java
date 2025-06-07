package com.megabus.BusBooking.repository;

import com.megabus.BusBooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
