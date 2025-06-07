package com.megabus.BusBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;
    private String busType;  // ac, non-ac, sleeper
    private Integer busCapacity;
    private String busOperator;
}
