package com.megabus.BusBooking.service;

import com.megabus.BusBooking.entity.Booking;
import com.megabus.BusBooking.entity.Schedule;
import com.megabus.BusBooking.entity.Users;
import com.megabus.BusBooking.repository.BookingRepository;
import com.megabus.BusBooking.repository.ScheduleRepository;
import com.megabus.BusBooking.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(Long userId, Long scheduleId, Integer seatNumber) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));

        if (schedule.getAvailableSeats() < seatNumber) {
            throw new RuntimeException("Seats not available");
        }

        Booking booking = new Booking();
        booking.setUsers(user);
        booking.setSchedule(schedule);
        booking.setSeatNumber(seatNumber);
        booking.setBookingStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
}