package by.academy.it.travelcompany.service;

import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RouteMap;

import java.util.List;

public interface FlightService {

    Flight createOrUpdate(Flight flight);

    List<Flight> getAllByRouteMap(RouteMap routeMap);

}
