package com.megabus.BusBooking.controller;

import com.megabus.BusBooking.entity.Booking;
import com.megabus.BusBooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public ResponseEntity<Booking> bookSeat(
            @PathVariable Long userId,
            @PathVariable Long scheduleId,
            @PathVariable Integer seatNumber){

        Booking booking = bookingService.createBooking(userId, scheduleId, seatNumber);
        return ResponseEntity.ok(booking);

    }


}
