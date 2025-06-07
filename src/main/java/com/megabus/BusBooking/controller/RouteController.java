package com.megabus.BusBooking.controller;

import com.megabus.BusBooking.dto.RouteSearchDTO;
import com.megabus.BusBooking.entity.Route;
import com.megabus.BusBooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/saveRoute")
    public ResponseEntity<List<Route>> routeSearch(@RequestBody RouteSearchDTO routeSearchDTO){
        List<Route> routes = routeService.searchRoute(routeSearchDTO);
        return ResponseEntity.ok(routes);
    }

}
