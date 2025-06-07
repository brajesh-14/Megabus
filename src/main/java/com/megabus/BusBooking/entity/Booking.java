package com.megabus.BusBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    private Integer seatNumber;
    private String bookingStatus;    //confirmed, cancelled

    private LocalDateTime bookingDate;
}
