package com.megabus.BusBooking.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RouteSearchDTO {

    private String source;
    private String destination;
    private LocalDate doj;
}
