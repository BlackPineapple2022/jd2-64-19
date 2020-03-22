package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RouteMap;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    Flight createOrUpdate(Flight flight);

    List<Flight> getAllByRouteMap(RouteMap routeMap);

    List<Flight> getAllByRouteMapBetweenDateTime(RouteMap routeMap, LocalDate localDateStartL, LocalDate localDateEndL);
}
