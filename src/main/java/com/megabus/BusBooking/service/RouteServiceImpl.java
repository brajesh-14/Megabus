package com.megabus.BusBooking.service;

import com.megabus.BusBooking.dto.RouteSearchDTO;
import com.megabus.BusBooking.entity.Route;
import com.megabus.BusBooking.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> searchRoute(RouteSearchDTO dto){
        // सबसे पहले repository से matching routes निकालो
        List<Route> routes = routeRepository.findBySourceAndDestinationAndDoj(
                dto.getSource(),
                dto.getDestination(),
                dto.getDoj()
        );

        // अगर कोई भी route नहीं मिला, तो नया create करके save करो
        if (routes.isEmpty()) {
            Route newRoute = new Route();
            newRoute.setSource(dto.getSource());
            newRoute.setDestination(dto.getDestination());
            newRoute.setDoj(dto.getDoj());

            routeRepository.save(newRoute);

            // save होने के बाद updated list निकालो
            routes = routeRepository.findBySourceAndDestinationAndDoj(
                    dto.getSource(),
                    dto.getDestination(),
                    dto.getDoj()
            );
        }

        return routes;
    }
}
