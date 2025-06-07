package com.megabus.BusBooking.repository;

import com.megabus.BusBooking.dto.RouteSearchDTO;
import com.megabus.BusBooking.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findBySourceAndDestinationAndDoj(String source, String destination, LocalDate doj);
}
