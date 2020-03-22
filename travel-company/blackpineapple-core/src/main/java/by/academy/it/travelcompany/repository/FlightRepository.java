package by.academy.it.travelcompany.repository;

import by.academy.it.travelcompany.entity.Flight;
import by.academy.it.travelcompany.entity.RouteMap;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Serializable> {

    Flight getByRouteMapAndDepartureDateTimeAndFlightNumber(RouteMap routeMap, LocalDateTime departureDateTime, String flightNumber);

    List<Flight> getAllByRouteMap(RouteMap routeMap);

    List<Flight> getAllByRouteMapAndArriveDateTimeIsAfterAndArriveDateTimeIsBefore(RouteMap r, LocalDateTime start,LocalDateTime finish);

}
