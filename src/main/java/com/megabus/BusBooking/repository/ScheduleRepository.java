package com.megabus.BusBooking.repository;

import com.megabus.BusBooking.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
