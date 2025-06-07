package com.megabus.BusBooking.service;

import com.megabus.BusBooking.dto.RouteSearchDTO;
import com.megabus.BusBooking.entity.Route;

import java.util.List;

public interface RouteService {

    public List<Route> searchRoute(RouteSearchDTO routeSearchDTO);
}
