package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.flight.Flight;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface FlightService {

    Flight create(Flight flight);

    Optional<Flight> read(Long id);

    Flight update(Flight flight);

    void delete(Long id);

    List <Flight> getAll();

    List<Flight> getAllFlightBySearchId(Long searchId);

    void updateByDateAndFlightNumberOrCreate(Flight flight);

    void updateByDateAndFlightNumberOrCreate(List<Flight> list);


}
