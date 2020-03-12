package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RouteMap;
import by.academy.it.travelcompany.repository.FlightRepository;
import by.academy.it.travelcompany.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public Flight createOrUpdate(Flight flight) {
        log.info("Creating or updating flight " + flight);
        LocalDateTime departureDateTime = flight.getDepartureDateTime();
        RouteMap routeMap = flight.getRouteMap();
        String flightNumber = flight.getFlightNumber();
        Flight flightFromRepository = flightRepository.getByRouteMapAndDepartureDateTimeAndFlightNumber(routeMap, departureDateTime, flightNumber);
        if (flightFromRepository==null){
            flight.setUpdateDateTime(LocalDateTime.now());
            return flightRepository.save(flight);
        } else {
            flightFromRepository.setPrice(flight.getPrice());
            flightFromRepository.setUpdateDateTime(LocalDateTime.now());
            return flightRepository.save(flightFromRepository);
        }

    }

    @Override
    public List<Flight> getAllByRouteMap(RouteMap routeMap) {
        log.info("Getting all flight by route map" + routeMap);
        return flightRepository.getAllByRouteMap(routeMap);
    }


}
