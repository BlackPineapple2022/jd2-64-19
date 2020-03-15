package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelcompany.travelitem.flight.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightService {

    Flight create(Flight flight);

    Optional<Flight> read(Long id);

    Flight update(Flight flight);

    void delete(Long id);

    List<Flight> getAll();

    List<Flight> getAllFlightBySearchId(Long searchId);

    void updateByDateAndFlightNumberOrCreate(Flight flight);

    void updateByDateAndFlightNumberOrCreate(List<Flight> list);

   List<Flight> getFlightListByRouteMapIdAndDates(Long routeMapId, LocalDate firstDate, LocalDate secondDate);

}
