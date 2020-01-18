package by.academy.it.travelcompany.service.global;

import by.academy.it.travelcompany.travelitem.airline.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {

//CRUD

    Airline create(Airline airline);

    Optional<Airline> read(Long id);

    Airline update(Airline airline);

    void delete(Long id);

//!CRUD

    List<Airline> getAll();

    void installAllAirline();

    Long getIdByName(String airlineName);

}
